package com.example.composetask.presentation.feature.authentication.register

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun RegisterScreen(navController: NavHostController, viewModel: UserViewModel = hiltViewModel()) {
    RegisterView(registerViewModel = viewModel, navController = navController)
}
