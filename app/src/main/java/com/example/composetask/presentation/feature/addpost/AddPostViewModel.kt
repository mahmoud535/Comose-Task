package com.example.composetask.presentation.feature.addpost

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.composetask.domain.model.Product
import com.example.composetask.domain.usecase.AddProductUseCase
import javax.inject.Inject
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File

@HiltViewModel
class AddPostViewModel @Inject constructor( private val addProductUseCase: AddProductUseCase): ViewModel() {
    var postProductResult by mutableStateOf<Result<Product>?>(null)

    fun postProduct(
        id: Int,
        title: String,
        price: String,
        category: String,
        description: String,
        imageFile: File
    ) {
        viewModelScope.launch {
            postProductResult = addProductUseCase.execute(id, title, price, category, description, imageFile)
        }
    }
}