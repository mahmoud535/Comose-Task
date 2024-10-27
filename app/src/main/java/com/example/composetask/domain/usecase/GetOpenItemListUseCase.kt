package com.example.composetask.domain.usecase

import com.example.composetask.domain.model.OpenItem
import com.example.composetask.domain.model.ServiceItem
import com.example.composetask.domain.repository.Repository
import javax.inject.Inject

class GetOpenItemListUseCase @Inject constructor(private val repository: Repository) {
    suspend fun invoke(): List<OpenItem> {
        return repository.getOpenItemsList()
    }
}