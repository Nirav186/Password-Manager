package com.password.manager.navigation

sealed class Screen(var route: String) {
    data object HomeScreen : Screen("HomeScreen")
}