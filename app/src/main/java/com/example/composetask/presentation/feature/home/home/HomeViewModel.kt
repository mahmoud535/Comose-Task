package com.example.composetask.presentation.feature.home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetask.domain.model.HomeDataItems
import com.example.composetask.domain.usecase.GetHomeItemListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeItemsUseCase: GetHomeItemListUseCase,
) : ViewModel() {

    private val _homeItems = MutableStateFlow<List<HomeDataItems>>(emptyList())
    val homeItems: StateFlow<List<HomeDataItems>> get() = _homeItems

    init {
        fetchHomeItems()
    }

    private fun fetchHomeItems() {
        viewModelScope.launch {
            _homeItems.value = getHomeItemsUseCase.invoke()
        }
    }
}