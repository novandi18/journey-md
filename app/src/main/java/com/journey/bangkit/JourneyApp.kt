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
import com.journey.bangkit.data.source.JourneyDataSource
import com.journey.bangkit.ui.component.BottomBar
import com.journey.bangkit.ui.navigation.JobSeekerNavigation
import com.journey.bangkit.ui.navigation.NavigationItem
import com.journey.bangkit.ui.navigation.Screen
import com.journey.bangkit.ui.navigation.authGraph
import com.journey.bangkit.ui.navigation.introGraph
import com.journey.bangkit.ui.navigation.jobProviderGraph
import com.journey.bangkit.ui.navigation.jobSeekerGraph

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun JourneyApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onBoardingSkip: Boolean,
    bottomBarState: Boolean,
    setBottomBarState: (Boolean) -> Unit,
    setNavigationItem: (List<NavigationItem>) -> Unit,
    navigationItem: List<NavigationItem>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    when (navBackStackEntry?.destination?.route) {
        Screen.OnBoarding.route -> setBottomBarState(false)
        Screen.Started.route -> setBottomBarState(false)
        Screen.LoginJobSeeker.route -> setBottomBarState(false)
        Screen.LoginJobProvider.route -> setBottomBarState(false)
        Screen.RegisterJobSeeker.route -> setBottomBarState(false)
        Screen.RegisterJobProvider.route -> setBottomBarState(false)
        Screen.HomeJobSeeker.route -> setBottomBarState(true)
        Screen.HomeJobProvider.route -> setBottomBarState(true)
        Screen.JobApply.route -> setBottomBarState(true)
        Screen.JobApplicant.route -> setBottomBarState(true)
        Screen.ProfileJobSeeker.route -> setBottomBarState(true)
        Screen.ProfileJobProvider.route -> setBottomBarState(true)
        Screen.AddVacancy.route -> setBottomBarState(false)
        Screen.DetailVacancy.route -> setBottomBarState(false)
    }

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                bottomBarState = bottomBarState,
                navigationItems = JourneyDataSource.navItemsJobSeeker
            )
        },
        content = { innerPadding ->
            AnimatedNavHost(
                navController = navController,
                modifier = modifier.padding(innerPadding),
                startDestination = JobSeekerNavigation.JOB_SEEKER_ROUTE
            ) {
                introGraph(navController)
                authGraph(navController)
                jobSeekerGraph(navController, setBottomBarState)
                jobProviderGraph(navController, setBottomBarState)
            }
        }
    )
}
