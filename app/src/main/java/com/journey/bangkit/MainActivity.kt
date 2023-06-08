package com.journey.bangkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.journey.bangkit.data.store.JourneyDataStore
import com.journey.bangkit.ui.theme.JourneyTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(DelicateCoroutinesApi::class)
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
                isOnBoardingSkip = it
            }
        }

        setContent {
            JourneyTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    JourneyApp(
                        onBoardingSkip = isOnBoardingSkip
                    )
                }
            }
        }
    }
}