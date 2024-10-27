package com.example.composetask.presentation.feature.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetask.R
import com.example.composetask.domain.model.Category
import com.example.composetask.domain.model.ServiceItem

@Composable
fun DetailsView(serviceItem: ServiceItem) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = serviceItem.imageRes),
            contentDescription = serviceItem.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = serviceItem.name,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic,
                        background = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "11.50$", style = TextStyle(
                        shadow = androidx.compose.ui.graphics.Shadow(
                            color = Color.Green,
                            blurRadius = 10f
                        ),
                        background = Color.White,
                        color = Color.Green,
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Category: ${serviceItem.category.categoryName}",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Normal,
                    background = Color.White
                )
            )
        }

    }

}

@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFF000000)
@Composable
fun DetailsPreview(){
    DetailsView(ServiceItem(1,"name", R.drawable.img1, Category(1,"Category1")))
}