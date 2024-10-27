package com.example.composetask.presentation.utils

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composetask.R
import com.example.composetask.presentation.utils.navigation.Screen

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    NavigationBar(
        containerColor = Color.White,
        modifier = modifier
            .shadow(elevation = 8.dp)
    ) {
        NavigationBarItem(
            selected = navController.currentDestination?.route == Screen.Home.route,
            onClick = {
                navController.navigate(Screen.Home.route) {
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home",
                    tint = if (navController.currentDestination?.route == Screen.Home.route) Color.Black else Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = "Home", fontWeight = FontWeight.Bold,
                    color = if (navController.currentDestination?.route == Screen.Home.route) Color.Black else Color.Black
                )
            }
        )

        NavigationBarItem(
            selected = navController.currentDestination?.route == Screen.Services.route,
            onClick = {
                navController.navigate(Screen.Services.route) {
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.time),
                    contentDescription = "Services",
                    tint = if (navController.currentDestination?.route == Screen.Services.route) Color.Black else Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = "Services",fontWeight = FontWeight.Bold,
                    color = if (navController.currentDestination?.route == Screen.Services.route) Color.Black else Color.Gray
                )
            }
        )

        NavigationBarItem(
            selected = navController.currentDestination?.route == Screen.Discover.route,
            onClick = {
                navController.navigate(Screen.Discover.route) {
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "Discover",
                    tint = if (navController.currentDestination?.route == Screen.Discover.route) Color.Black else Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = "Discover",fontWeight = FontWeight.Bold,
                    color = if (navController.currentDestination?.route == Screen.Discover.route) Color.Black else Color.Gray
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    BottomNavigationBar(navController = NavController(LocalContext.current))
}
