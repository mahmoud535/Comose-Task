package com.example.composetask.presentation.feature.home.services.servicecomponent

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetask.domain.model.OpenItem
import com.example.composetask.presentation.feature.details.DetailsActivity


@Composable
fun OpenCardList(openListItems: List<OpenItem>){
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(openListItems) { item ->
            OpenCard(item) {
                val intent = Intent(context, DetailsActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}

@Composable
fun OpenCard(openItem: OpenItem, onCardClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .border(1.dp, Color.LightGray, RoundedCornerShape(16.dp))
            .clickable(onClick = onCardClick),
        colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = openItem.imageRes),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = openItem.title,
                    fontSize = 16.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
                Text(text = openItem.subTitle1, fontSize = 14.sp)
                Text(text = openItem.subTitle2, fontSize = 14.sp)
                Text(text = openItem.subTitle3, fontSize = 14.sp)
            }
            IconButton(onClick = { /* Action */ }) {
                Icon(Icons.Default.KeyboardArrowRight, contentDescription = "More Options")
            }
        }
    }
}