package com.example.composetask.domain.model.user

data class UserResponse(
    val id: Int,
    val email: String,
    val username: String,
    val name: Name,
    val address: Address,
    val phone: String,
    val createdAt: String // Timestamp of when the user was created
)