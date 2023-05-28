package com.journey.bangkit

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.journey.bangkit.data.store.JourneyDataStore
import com.journey.bangkit.ui.navigation.Screen
import com.journey.bangkit.ui.screen.home.HomeScreen
import com.journey.bangkit.ui.screen.login.jobprovider.LoginJobProviderScreen
import com.journey.bangkit.ui.screen.login.jobseeker.LoginJobSeekerScreen
import com.journey.bangkit.ui.screen.onboarding.OnBoardingScreen
import com.journey.bangkit.ui.screen.register.jobprovider.RegisterJobProviderScreen
import com.journey.bangkit.ui.screen.register.jobseeker.RegisterJobSeekerScreen
import com.journey.bangkit.ui.screen.started.StartedScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun JourneyApp(
    navController: NavHostController = rememberAnimatedNavController()
) {
    val dataStore = JourneyDataStore(LocalContext.current)
    val isOnBoardingCompleted = dataStore.isOnBoardingCompleted.collectAsState(initial = false)

    AnimatedNavHost(
        navController = navController,
        startDestination = if (isOnBoardingCompleted.value)
            Screen.Started.route else Screen.OnBoarding.route
    ) {
        composable(Screen.OnBoarding.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.Started.route ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                    else -> null
                }
            }
        ) {
            OnBoardingScreen(
                navigateToStarted = {
                    navController.navigate(Screen.Started.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Screen.Started.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.OnBoarding.route ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                    Screen.LoginJobSeeker.route ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                    Screen.LoginJobProvider.route ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                    else -> null
                }
            }
        ) {
            StartedScreen(
                navigateToJobSeeker = {
                    navController.navigate(Screen.LoginJobSeeker.route)
                },
                navigateToJobProvider = {
                    navController.navigate(Screen.LoginJobProvider.route)
                }
            )
        }
        composable(Screen.LoginJobSeeker.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.Started.route ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                    Screen.RegisterJobSeeker.route ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                    else -> null
                }
            }
        ) {
            LoginJobSeekerScreen(
                backToStarted = {
                    navController.navigateUp()
                },
                navigateToRegister = {
                    navController.navigate(Screen.RegisterJobSeeker.route)
                }
            )
        }
        composable(Screen.LoginJobProvider.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.Started.route ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                    Screen.RegisterJobProvider.route ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                    else -> null
                }
            }
        ) {
            LoginJobProviderScreen(
                backToStarted = {
                    navController.navigateUp()
                },
                navigateToRegister = {
                    navController.navigate(Screen.RegisterJobProvider.route)
                }
            )
        }
        composable(Screen.RegisterJobSeeker.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.LoginJobSeeker.route ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                    else -> null
                }
            },
        ) {
            RegisterJobSeekerScreen(
                backToLogin = {
                    navController.navigateUp()
                }
            )
        }
        composable(Screen.RegisterJobProvider.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.LoginJobProvider.route ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                    else -> null
                }
            }
        ) {
            RegisterJobProviderScreen(
                backToLogin = {
                    navController.navigateUp()
                }
            )
        }
        composable(Screen.Home.route, enterTransition = {null}) {
            HomeScreen()
        }
    }
}
