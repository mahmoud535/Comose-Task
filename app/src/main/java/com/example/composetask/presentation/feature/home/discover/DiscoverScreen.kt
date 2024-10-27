package com.example.composetask.presentation.feature.home.discover

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun DiscoverScreen(navController: NavController, viewModel: DiscoverViewModel = hiltViewModel()) {
    DiscoverView(viewModel = viewModel)
}

