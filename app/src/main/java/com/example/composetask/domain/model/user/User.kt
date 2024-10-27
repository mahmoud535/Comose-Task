package com.example.composetask.domain.model.user

data class User(
    val email: String,
    val username: String,
    val password: String,
    val name: Name,
    val address: Address,
    val phone: String
)

