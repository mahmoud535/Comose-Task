package com.example.composetask.presentation.utils.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Services : Screen("services")
    object Discover : Screen("discover")
    object Login : Screen("login")
    object Register : Screen("register")
    object AddPost : Screen("addpost")

}