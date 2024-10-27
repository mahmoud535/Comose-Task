package com.example.composetask.presentation.feature.home.Main

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.composetask.presentation.utils.BottomNavigationBar
import com.example.composetask.presentation.utils.SideMenu
import com.example.composetask.presentation.utils.TopAppBar
import com.example.composetask.presentation.utils.navigation.SetUpNavGraph

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideMenu(navController)
        }
    ) {
        Scaffold(
            topBar = { TopAppBar(navController, drawerState, scope) },
            bottomBar = { BottomNavigationBar(Modifier, navController) },
        ) { innerPadding ->
            SetUpNavGraph(navController = navController , innerPadding = innerPadding)
        }
    }
}
