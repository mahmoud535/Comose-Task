package com.example.composetask.domain.usecase

import com.example.composetask.domain.model.ServiceItem
import com.example.composetask.domain.repository.Repository
import javax.inject.Inject

class GetServiceItemsUseCase @Inject constructor(private val repository: Repository) {
    suspend fun invoke(): List<ServiceItem> {
        return repository.getServiceItems()
    }
}