package com.example.composetask.presentation.feature.authentication.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetask.data.local.SharedPrefsHelper
import kotlinx.coroutines.launch
import com.example.composetask.domain.model.LoginRequest
import com.example.composetask.domain.model.LoginResponse
import com.example.composetask.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository,
    private val application: Application
) : ViewModel() {

    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> = _loginResult

    fun login(loginRequest: LoginRequest) = viewModelScope.launch {
        try {
            val response = repository.login(loginRequest)
            if (response.isSuccessful && response.body() != null) {
                val loginResponse = response.body()?.token
                Log.d("LoginViewModel", "Access Token: $loginResponse")
                loginResponse?.let {
                    SharedPrefsHelper.saveAccessToken(application.applicationContext, it)
                }
                _loginResult.value = Result.success(response.body()!!)
            } else {
                _loginResult.value = Result.failure(Exception("LoginRequest failed with code: ${response.code()}"))
            }
        } catch (e: Exception) {
            _loginResult.value = Result.failure(e)
        }
    }
}
