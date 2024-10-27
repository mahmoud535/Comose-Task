package com.example.composetask.presentation.feature.addpost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.composetask.presentation.feature.home.Main.MainNavigation
import com.example.composetask.presentation.utils.navigation.AuthenticationNavGraph
import com.example.composetask.presentation.utils.navigation.SetUpNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AddPostScreen(navController = navController  )
        }
    }
}
