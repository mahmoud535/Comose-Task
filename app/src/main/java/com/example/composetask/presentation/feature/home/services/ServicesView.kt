package com.example.composetask.presentation.feature.home.services

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.coroutines.launch
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.example.composetask.presentation.feature.home.services.servicecomponent.OpenCardList
import com.example.composetask.presentation.feature.home.services.servicecomponent.ServiceCardList


@Composable
fun ServicesView(viewModel: ServicesViewModel) {
    val serviceItems by viewModel.serviceItems.collectAsState()
    val openListItems by viewModel.openListItems.collectAsState()

    val pagerState = rememberPagerState(pageCount = { 3 })

    Column {
        val scope = rememberCoroutineScope()
        TabRow(selectedTabIndex = pagerState.currentPage) {
            Tab(
                text = { Text("Service") },
                selected = pagerState.currentPage == 0,
                onClick = {
                    scope.launch {
                        pagerState.scrollToPage(0)
                    }
                }
            )
            Tab(
                text = { Text("Open") },
                selected = pagerState.currentPage == 1,
                onClick = {
                    scope.launch {
                        pagerState.scrollToPage(1)
                    }
                }
            )
            Tab(
                text = { Text("History") },
                selected = pagerState.currentPage == 2,
                onClick = {
                    scope.launch {
                        pagerState.scrollToPage(2)
                    }
                }
            )
        }

        HorizontalPager(
            state = pagerState,
        ) { page ->
            when (page) {
                0 -> ServiceCardList(serviceItems)
                1 ->  OpenCardList(openListItems)
                2 -> Text("History List")
            }
        }
    }
}