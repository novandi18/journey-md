package com.journey.bangkit.ui.navigation

sealed class Screen(val route: String) {
    object OnBoarding : Screen("onboarding")
    object Home : Screen("home")
    object Started : Screen("started")
    object LoginJobSeeker : Screen("login_jobseeker")
    object LoginJobProvider : Screen("login_jobprovider")
    object RegisterJobSeeker : Screen("register_jobseeker")
    object RegisterJobProvider : Screen("register_jobprovider")
}