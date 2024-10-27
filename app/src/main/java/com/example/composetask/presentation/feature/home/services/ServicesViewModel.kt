package com.example.composetask.presentation.feature.home.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetask.domain.model.OpenItem
import com.example.composetask.domain.model.ServiceItem
import com.example.composetask.domain.usecase.GetOpenItemListUseCase
import com.example.composetask.domain.usecase.GetServiceItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServicesViewModel @Inject constructor(
    private val getServiceItemsUseCase: GetServiceItemsUseCase,
    private val getOpenListItems: GetOpenItemListUseCase,
) : ViewModel() {

    private val _serviceItems = MutableStateFlow<List<ServiceItem>>(emptyList())
    val serviceItems: StateFlow<List<ServiceItem>> get() = _serviceItems

    private val _openListItems = MutableStateFlow<List<OpenItem>>(emptyList())
    val openListItems: StateFlow<List<OpenItem>> get() = _openListItems

    init {
        fetchServiceItems()
        fetchOpenListItems()
    }

    private fun fetchServiceItems() {
        viewModelScope.launch {
            _serviceItems.value = getServiceItemsUseCase.invoke()
        }
    }

    private fun fetchOpenListItems() {
        viewModelScope.launch {
            _openListItems.value = getOpenListItems.invoke()
        }
    }
}