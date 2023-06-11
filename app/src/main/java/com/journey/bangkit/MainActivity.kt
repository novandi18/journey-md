package com.journey.bangkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.journey.bangkit.data.store.JourneyDataStore
import com.journey.bangkit.ui.navigation.NavigationItem
import com.journey.bangkit.ui.theme.JourneyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    @OptIn(DelicateCoroutinesApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStore = JourneyDataStore(applicationContext)
        var keepSplashOnScreen = true
        var isOnBoardingSkip = false
        installSplashScreen().apply {
            setKeepOnScreenCondition { keepSplashOnScreen }
        }
        GlobalScope.launch {
            dataStore.isOnBoardingCompleted.collect {
                keepSplashOnScreen = false
                dataStore.isLoginAsJobSeeker.collect {
                    isOnBoardingSkip = it

                }
            }
        }

        setContent {
            JourneyTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    navController = rememberAnimatedNavController()
                    val bottomBarState = rememberSaveable { mutableStateOf(false) }
                    val navigationItems = rememberSaveable { mutableStateOf(listOf<NavigationItem>()) }
                    JourneyApp(
                        onBoardingSkip = isOnBoardingSkip,
                        navController = navController,
                        bottomBarState = bottomBarState.value,
                        setBottomBarState = {
                            bottomBarState.value = it
                        },
                        setNavigationItem = {
                            navigationItems.value = it
                        },
                        navigationItem = navigationItems.value
                    )
                }
            }
        }
    }
}