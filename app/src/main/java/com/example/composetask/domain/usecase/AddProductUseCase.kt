package com.example.composetask.domain.usecase

import com.example.composetask.domain.model.Product
import com.example.composetask.domain.repository.Repository
import retrofit2.Response
import java.io.File
import javax.inject.Inject

class AddProductUseCase @Inject constructor(private val repository: Repository) {
//    suspend operator fun invoke(product: Product): Response<Product> {
//        return repository.addPosts(product)
//    }

    suspend fun execute(
        id: Int,
        title: String,
        price: String,
        category: String,
        description: String,
        imageFile: File
    ): Result<Product> {
        return try {
            val response = repository.postProduct(id, title, price, category, description, imageFile)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error posting product"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}