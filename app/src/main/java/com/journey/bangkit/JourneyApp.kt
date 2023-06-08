package com.journey.bangkit

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.journey.bangkit.ui.component.BottomBar
import com.journey.bangkit.ui.navigation.JobProviderNavigation
import com.journey.bangkit.ui.navigation.authGraph
import com.journey.bangkit.ui.navigation.introGraph
import com.journey.bangkit.ui.navigation.jobProviderGraph
import com.journey.bangkit.ui.navigation.jobSeekerGraph

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun JourneyApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController(),
    onBoardingSkip: Boolean
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                navigation = JobProviderNavigation.JOB_PROVIDER_ROUTE,
                currentRoute = currentRoute
            )
        }
    ) { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            modifier = modifier.padding(innerPadding),
            startDestination = JobProviderNavigation.JOB_PROVIDER_ROUTE
        ) {
            introGraph(navController)
            authGraph(navController)
            jobSeekerGraph(navController)
            jobProviderGraph(navController)
        }
    }
}
