package com.example.composetask.presentation.feature.authentication.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetask.domain.model.user.User
import com.example.composetask.domain.model.user.UserResponse
import com.example.composetask.domain.usecase.AddUserUseCase
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val addUserUseCase: AddUserUseCase): ViewModel() {
    private val _registrationState = mutableStateOf<Result<UserResponse>?>(null)
    val registrationState: State<Result<UserResponse>?> get() = _registrationState

    fun registerUser(user: User) {
        viewModelScope.launch {
            try {
                val result = addUserUseCase(user)
                _registrationState.value = Result.success(result)
            } catch (e: Exception) {
                _registrationState.value = Result.failure(e)
            }
        }
    }
}