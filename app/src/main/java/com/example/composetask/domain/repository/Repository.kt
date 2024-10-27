package com.example.composetask.domain.repository

import com.example.composetask.domain.model.HomeDataItems
import com.example.composetask.domain.model.LoginRequest
import com.example.composetask.domain.model.LoginResponse
import com.example.composetask.domain.model.OpenItem
import com.example.composetask.domain.model.Product
import com.example.composetask.domain.model.ServiceItem
import com.example.composetask.domain.model.user.User
import com.example.composetask.domain.model.user.UserResponse
import retrofit2.Response
import java.io.File

interface Repository {
    suspend fun getServiceItems(): List<ServiceItem>
    suspend fun getOpenItemsList(): List<OpenItem>
    suspend fun getHomeItemsList(): List<HomeDataItems>
    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse>
    suspend fun addUser(user: User): UserResponse
    suspend fun postProduct(
        id: Int,
        title: String,
        price: String,
        category: String,
        description: String,
        imageFile: File
    ): Response<Product>

}