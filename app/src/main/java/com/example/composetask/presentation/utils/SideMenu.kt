package com.example.composetask.presentation.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SideMenu(navController: NavController) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight()
            .width(300.dp)
    ) {
        Column(modifier = Modifier.padding(top = 40.dp, start = 30.dp,end = 10.dp)) {
            MenuItem(
                icon = Icons.Filled.Home,
                text = "Menu Item 1",
                onClick = { }
            )
            Spacer(modifier = Modifier.height(8.dp))
            MenuItem(
                icon = Icons.Filled.Build,
                text = "Menu Item 2",
                onClick = { }
            )
            Spacer(modifier = Modifier.height(8.dp))
            MenuItem(
                icon = Icons.Filled.AccountCircle,
                text = "Menu Item 3",
                onClick = {  }
            )
        }
    }
}

@Composable
fun MenuItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SideMenuPreview(){
    SideMenu(navController = NavController(LocalContext.current))
}