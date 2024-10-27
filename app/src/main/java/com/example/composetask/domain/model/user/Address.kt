package com.example.composetask.domain.model.user

data class Address(
    val city: String,
    val street: String,
    val number: Int,
    val zipcode: String,
    val geolocation: Geolocation
)
