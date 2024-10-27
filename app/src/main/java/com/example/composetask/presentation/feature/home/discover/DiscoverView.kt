package com.example.composetask.presentation.feature.home.discover

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.example.composetask.presentation.feature.home.services.servicecomponent.ServiceCardList
import kotlinx.coroutines.launch

@Composable
fun DiscoverView(viewModel: DiscoverViewModel) {
    val serviceItems by viewModel.serviceItems.collectAsState()

    val pagerState = rememberPagerState(pageCount = { 2 })
    Column {
        val scope = rememberCoroutineScope()
        TabRow(selectedTabIndex = pagerState.currentPage) {
            Tab(
                text = { Text("Internal") },
                selected = pagerState.currentPage == 0,
                onClick = {
                    scope.launch() {
                        pagerState.scrollToPage(0)
                    }
                }
            )
            Tab(
                text = { Text("External") },
                selected = pagerState.currentPage == 1,
                onClick = {
                    scope.launch() {
                        pagerState.scrollToPage(1)
                    }
                }
            )
        }

        HorizontalPager(
            state = pagerState,
        ) { page ->
            when (page) {
                0 -> ServiceCardList(serviceItems)
                1 -> Text("External content here")
            }
        }
    }
}
