package com.journey.bangkit.ui.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.journey.bangkit.data.source.JourneyDataSource
import com.journey.bangkit.ui.navigation.Screen
import com.journey.bangkit.ui.theme.JourneyTheme

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    isJobSeeker: Boolean
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val navigationItems = if (isJobSeeker) JourneyDataSource.navItemsJobSeeker else JourneyDataSource.navItemsJobProvider

    if (((currentRoute != Screen.OnBoarding.route &&
        currentRoute != Screen.Started.route) &&
        (currentRoute != Screen.LoginJobSeeker.route &&
        currentRoute != Screen.LoginJobProvider.route)) &&
        (currentRoute != Screen.RegisterJobSeeker.route &&
        currentRoute != Screen.RegisterJobProvider.route)) {

        NavigationBar(
            modifier = modifier
                .fillMaxWidth()
                .shadow(elevation = 16.dp),
            containerColor = MaterialTheme.colorScheme.background
        ) {
            navigationItems.map { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = stringResource(id = item.contentDescription)
                        )
                    },
                    selected = currentRoute == item.screen.route,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        selectedIconColor = Color.White,
                        unselectedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = .5f),
                        unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = .5f)
                    ),
                    label = {
                        Text(
                            text = stringResource(id = item.title),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
private fun BottomBarPreview() {
    JourneyTheme {
        BottomBar(
            navController = rememberAnimatedNavController(),
            isJobSeeker = false
        )
    }
}