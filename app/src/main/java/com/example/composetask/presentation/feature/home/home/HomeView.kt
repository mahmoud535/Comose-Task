package com.example.composetask.presentation.feature.home.home

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.composetask.R
import com.example.composetask.domain.model.HomeDataItems
import com.example.composetask.data.remote.ApiService
import com.example.composetask.data.repository.RepositoryImp
import com.example.composetask.domain.usecase.GetHomeItemListUseCase
import com.example.composetask.presentation.feature.addpost.PostActivity
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState


@Composable
fun HomeView(viewModel: HomeViewModel) {
    val homeItems by viewModel.homeItems.collectAsState()
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "Hello fady",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        )
        Text(
            "Create Product",
            style = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.teal_700)
            )
        )
    }

    LazyColumn(
        modifier = Modifier.padding(top = 50.dp)
    ) {
        items(homeItems) { posts ->
            CardItem(posts)
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

        }

    }
    FloatActionButton(onClick = {
        val intent = Intent(context, PostActivity::class.java)
        context.startActivity(intent)
    })
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CardItem(homeDataItems: HomeDataItems) {
    val images = listOf(R.drawable.img1, R.drawable.img2, R.drawable.img3)
    val pagerState = rememberPagerState()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = homeDataItems.personImage!!), // استخدم صورة الملف الشخصي
                    contentDescription = "Profile Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = homeDataItems.personName!!,
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                    Text("12:30 PM", style = TextStyle(color = Color.Gray))
                }
            }
            IconButton(onClick = { }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More Options", tint = Color.Gray)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        ) {
            HorizontalPager(
                count = images.size,
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                Image(
                    painter = painterResource(id = images[page]),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
            }

            // Dots Indicator
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                activeColor = colorResource(id = R.color.teal_700),
                inactiveColor = colorResource(id = R.color.gray)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
        ) {
            IconButton(onClick = { }) {
                Icon(painter = painterResource(id = R.drawable.love),tint = Color.Unspecified,modifier = Modifier.size(20.dp),  contentDescription = "Favorite")
            }
            IconButton(onClick = { }) {
                Icon(painter = painterResource(id = R.drawable.chat),tint = Color.Unspecified, modifier = Modifier.size(20.dp), contentDescription = "Chat")
            }
        }
    }
}

@Composable
fun FloatActionButton(onClick:() -> Unit ){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 25.dp, end = 25.dp, bottom = 30.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier
                .border(5.dp, Color.White, CircleShape),
            containerColor = colorResource(id = R.color.teal_700),
            shape = CircleShape
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "More Options",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeViewPreview() {
    val apiService:ApiService ?= null
    HomeView(HomeViewModel(GetHomeItemListUseCase(RepositoryImp(apiService!!))))
}