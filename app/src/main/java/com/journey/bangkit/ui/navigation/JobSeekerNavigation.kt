package com.journey.bangkit.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import com.journey.bangkit.ui.screen.home.jobseeker.HomeJobSeekerScreen
import com.journey.bangkit.ui.screen.job.apply.JobApplyScreen
import com.journey.bangkit.ui.screen.profile.jobseeker.ProfileJobSeekerScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.jobSeekerGraph(navController: NavController) {
    navigation(startDestination = Screen.HomeJobSeeker.route, route = JobSeekerNavigation.JOB_SEEKER_ROUTE) {
        composable(Screen.HomeJobSeeker.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.JobApply.route ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                    Screen.ProfileJobSeeker.route ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                    else -> null
                }
            }
        ) {
            HomeJobSeekerScreen()
        }
        composable(Screen.ProfileJobSeeker.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.JobApply.route ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                    Screen.HomeJobSeeker.route ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                    else -> null
                }
            }
        ) {
            ProfileJobSeekerScreen()
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
    }
}

object JobSeekerNavigation {
    const val JOB_SEEKER_ROUTE = "job_seeker_route"
}