package com.journey.bangkit.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: Int,
    val icon: ImageVector,
    val screen: Screen,
    val contentDescription: Int
)