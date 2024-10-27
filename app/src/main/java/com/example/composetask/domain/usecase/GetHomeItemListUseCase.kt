package com.example.composetask.domain.usecase

import com.example.composetask.domain.model.HomeDataItems
import com.example.composetask.domain.model.OpenItem
import com.example.composetask.domain.repository.Repository
import javax.inject.Inject

class GetHomeItemListUseCase @Inject constructor(private val repository: Repository) {
    suspend fun invoke(): List<HomeDataItems> {
        return repository.getHomeItemsList()
    }
}