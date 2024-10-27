package com.example.composetask.data.repository

import com.example.composetask.data.local.ListData
import com.example.composetask.data.local.ListServiceItems
import com.example.composetask.data.local.OpenItemsList
import com.example.composetask.data.remote.ApiService
import com.example.composetask.domain.model.HomeDataItems
import com.example.composetask.domain.model.LoginRequest
import com.example.composetask.domain.model.LoginResponse
import com.example.composetask.domain.model.OpenItem
import com.example.composetask.domain.model.Product
import com.example.composetask.domain.model.ServiceItem
import com.example.composetask.domain.model.user.User
import com.example.composetask.domain.model.user.UserResponse
import com.example.composetask.domain.repository.Repository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import java.io.File
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val apiService: ApiService) : Repository {
    override suspend fun getServiceItems(): List<ServiceItem> {
        return ListServiceItems.serviceItems
    }

    override suspend fun getOpenItemsList(): List<OpenItem> {
        return OpenItemsList.openItems
    }

    override suspend fun getHomeItemsList(): List<HomeDataItems> {
        return ListData.listItems
    }

    override suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return apiService.login(loginRequest)
    }

    override suspend fun addUser(user: User): UserResponse {
        return apiService.register(user)
    }

    override suspend fun postProduct(
        id: Int,
        title: String,
        price: String,
        category: String,
        description: String,
        imageFile: File
    ): Response<Product> {
        // Convert regular fields to RequestBody
        val idPart = RequestBody.create("text/plain".toMediaTypeOrNull(), id.toString())
        val titlePart = RequestBody.create("text/plain".toMediaTypeOrNull(), title)
        val pricePart = RequestBody.create("text/plain".toMediaTypeOrNull(), price)
        val categoryPart = RequestBody.create("text/plain".toMediaTypeOrNull(), category)
        val descriptionPart = RequestBody.create("text/plain".toMediaTypeOrNull(), description)

        // Convert image file to MultipartBody.Part
        val imageRequestBody = RequestBody.create("image/*".toMediaTypeOrNull(), imageFile)
        val imagePart = MultipartBody.Part.createFormData("image", imageFile.name, imageRequestBody)

        return apiService.postProduct(
            id = idPart,
            title = titlePart,
            price = pricePart,
            category = categoryPart,
            description = descriptionPart,
            image = imagePart
        )
    }
}