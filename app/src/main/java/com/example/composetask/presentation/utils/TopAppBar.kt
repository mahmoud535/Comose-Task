package com.example.composetask.presentation.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navController: NavController, drawerState: DrawerState, scope: CoroutineScope) {

    val currentBackStackEntry by navController.currentBackStackEntryFlow.collectAsState(initial = navController.currentBackStackEntry)
    val currentRoute = currentBackStackEntry?.destination?.route
    var showNotificationIcon by remember { mutableStateOf(false) }

    LaunchedEffect(currentBackStackEntry) {
        showNotificationIcon = currentRoute == "home"
    }


    androidx.compose.material3.TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when (currentRoute) {
                        "home" -> "Home"
                        "services" -> "Services"
                        "discover" -> "Discover"
                        else -> "App Name"
                    },
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        navigationIcon = {
            if (currentRoute == "home") {
                IconButton(onClick = {
                    scope.launch { drawerState.open() }
                }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                }
            } else {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        },
        actions = {
            if (showNotificationIcon) {
                IconButton(onClick = {  }) {
                    Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Color.Black)
                }
            }
        },
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@Preview
@Composable
fun TopAppBarPreview(){
    TopAppBar(navController = NavController(LocalContext.current), drawerState = rememberDrawerState(
        DrawerValue.Closed), scope = rememberCoroutineScope())
}



