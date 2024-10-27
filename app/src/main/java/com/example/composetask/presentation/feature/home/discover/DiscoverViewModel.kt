package com.example.composetask.presentation.feature.home.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetask.domain.model.ServiceItem
import com.example.composetask.domain.usecase.GetServiceItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(private val getServiceItemsUseCase: GetServiceItemsUseCase) : ViewModel() {
        private val _serviceItems = MutableStateFlow<List<ServiceItem>>(emptyList())
        val serviceItems: StateFlow<List<ServiceItem>> get() = _serviceItems

        init {
            fetchServiceItems()
        }

        private fun fetchServiceItems() {
            viewModelScope.launch {
                _serviceItems.value = getServiceItemsUseCase.invoke()
            }
        }
}