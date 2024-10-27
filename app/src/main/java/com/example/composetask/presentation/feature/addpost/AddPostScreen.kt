package com.example.composetask.presentation.feature.addpost

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composetask.presentation.feature.home.discover.DiscoverViewModel

@Composable
fun AddPostScreen (navController: NavController, viewModel: AddPostViewModel = hiltViewModel()) {
    AddPostView(viewModel = viewModel, navController = navController)
}