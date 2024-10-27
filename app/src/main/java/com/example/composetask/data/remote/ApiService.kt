package com.example.composetask.data.remote

import com.example.composetask.domain.model.LoginRequest
import com.example.composetask.domain.model.LoginResponse
import com.example.composetask.domain.model.Product
import com.example.composetask.domain.model.user.User
import com.example.composetask.domain.model.user.UserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("users")
    suspend fun register(@Body user: User): UserResponse

    @Multipart
    @POST("products")
    suspend fun postProduct(
        @Part("id") id: RequestBody,
        @Part("title") title: RequestBody,
        @Part("price") price: RequestBody,
        @Part("category") category: RequestBody,
        @Part("description") description: RequestBody,
        @Part image: MultipartBody.Part 
    ): Response<Product>

}