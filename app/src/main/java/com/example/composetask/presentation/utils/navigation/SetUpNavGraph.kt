package com.example.composetask.presentation.utils.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composetask.presentation.feature.addpost.AddPostScreen
import com.example.composetask.presentation.feature.authentication.login.LoginScreen
import com.example.composetask.presentation.feature.authentication.register.RegisterScreen
import com.example.composetask.presentation.feature.home.discover.DiscoverScreen
import com.example.composetask.presentation.feature.home.home.HomeScreen
import com.example.composetask.presentation.feature.home.services.ServicesScreen


@Composable
fun SetUpNavGraph(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
    navController = navController,
    startDestination = Screen.Home.route,
    modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = Screen.Home.route) { HomeScreen(navController = navController) }
        composable(route = Screen.Services.route) {ServicesScreen(navController = navController) }
        composable(route = Screen.Discover.route) { DiscoverScreen(navController = navController) }
    }
}

@Composable
fun AuthenticationNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) { LoginScreen(navController = navController) }
        composable(Screen.Register.route) { RegisterScreen(navController = navController) }
        composable(route = Screen.AddPost.route) { AddPostScreen(navController = navController) }
    }
}