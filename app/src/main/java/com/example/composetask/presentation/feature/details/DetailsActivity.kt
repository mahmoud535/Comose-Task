package com.example.composetask.presentation.feature.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.composetask.domain.model.ServiceItem

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val serviceItem = intent.getParcelableExtra<ServiceItem>("service")
        setContent {
            serviceItem?.let {
                DetailsView(it)
            }
        }
    }


}
