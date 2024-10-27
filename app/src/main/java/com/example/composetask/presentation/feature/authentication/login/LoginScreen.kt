package com.example.composetask.presentation.feature.authentication.login

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
    LoginView(viewModel = viewModel, navController = navController)
}
