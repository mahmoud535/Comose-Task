package com.example.composetask.domain.usecase

import com.example.composetask.domain.model.user.User
import com.example.composetask.domain.model.user.UserResponse
import com.example.composetask.domain.repository.Repository
import javax.inject.Inject

class AddUserUseCase @Inject constructor(private val userRepository: Repository) {
    suspend operator fun invoke(user: User): UserResponse {
        return userRepository.addUser(user)
    }
}