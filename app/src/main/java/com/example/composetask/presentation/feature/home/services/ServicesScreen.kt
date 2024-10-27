package com.example.composetask.presentation.feature.home.services

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ServicesScreen(navController: NavController, viewModel: ServicesViewModel = hiltViewModel()) {
    ServicesView(viewModel = viewModel)
}


