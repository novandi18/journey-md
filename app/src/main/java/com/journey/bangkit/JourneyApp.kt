package com.journey.bangkit

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.journey.bangkit.data.store.JourneyDataStore
import com.journey.bangkit.ui.component.BottomBar
import com.journey.bangkit.ui.navigation.Screen
import com.journey.bangkit.ui.screen.home.jobprovider.HomeJobProviderScreen
import com.journey.bangkit.ui.screen.home.jobseeker.HomeJobSeekerScreen
import com.journey.bangkit.ui.screen.job.applicant.JobApplicantScreen
import com.journey.bangkit.ui.screen.job.apply.JobApplyScreen
import com.journey.bangkit.ui.screen.login.jobprovider.LoginJobProviderScreen
import com.journey.bangkit.ui.screen.login.jobseeker.LoginJobSeekerScreen
import com.journey.bangkit.ui.screen.onboarding.OnBoardingScreen
import com.journey.bangkit.ui.screen.profile.jobprovider.ProfileJobProviderScreen
import com.journey.bangkit.ui.screen.profile.jobseeker.ProfileJobSeekerScreen
import com.journey.bangkit.ui.screen.register.jobprovider.RegisterJobProviderScreen
import com.journey.bangkit.ui.screen.register.jobseeker.RegisterJobSeekerScreen
import com.journey.bangkit.ui.screen.started.StartedScreen

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun JourneyApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController()
) {
    val dataStore = JourneyDataStore(LocalContext.current)
    val isOnBoardingCompleted = dataStore.isOnBoardingCompleted.collectAsState(initial = false)
    val isLoginAsJobSeeker = dataStore.isLoginAsJobSeeker.collectAsState(initial = false)
    val isLoginAsJobProvider = dataStore.isLoginAsJobProvider.collectAsState(initial = false)

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                isJobSeeker = isLoginAsJobSeeker.value
            )
        }
    ) { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            modifier = modifier.padding(innerPadding),
            startDestination = if (isOnBoardingCompleted.value) {
                if (isLoginAsJobSeeker.value) {
                    Screen.HomeJobSeeker.route
                } else if (isLoginAsJobProvider.value) {
                    Screen.HomeJobProvider.route
                } else Screen.Started.route
            } else {
                Screen.OnBoarding.route
            }
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
            composable(Screen.HomeJobSeeker.route,
                enterTransition = {
                    when (initialState.destination.route) {
                        Screen.JobApply.route ->
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                        else -> null
                    }
                }
            ) {
                HomeJobSeekerScreen()
            }
            composable(Screen.HomeJobProvider.route,
                enterTransition = {
                    when (initialState.destination.route) {
                        Screen.JobApplicant.route ->
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                        else -> null
                    }
                }
            ) {
                HomeJobProviderScreen()
            }
            composable(Screen.ProfileJobSeeker.route,
                enterTransition = {
                    when (initialState.destination.route) {
                        Screen.JobApply.route ->
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                        else -> null
                    }
                }
            ) {
                ProfileJobSeekerScreen()
            }
            composable(Screen.ProfileJobProvider.route,
                enterTransition = {
                    when (initialState.destination.route) {
                        Screen.JobApplicant.route ->
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                        else -> null
                    }
                }
            ) {
                ProfileJobProviderScreen()
            }
            composable(Screen.JobApply.route,
                enterTransition = {
                    when (initialState.destination.route) {
                        Screen.HomeJobSeeker.route ->
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                        Screen.ProfileJobSeeker.route ->
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                        else -> null
                    }
                }
            ) {
                JobApplyScreen()
            }
            composable(Screen.JobApplicant.route,
                enterTransition = {
                    when (initialState.destination.route) {
                        Screen.HomeJobProvider.route ->
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                        Screen.ProfileJobProvider.route ->
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                        else -> null
                    }
                }
            ) {
                JobApplicantScreen()
            }
        }
    }
}
