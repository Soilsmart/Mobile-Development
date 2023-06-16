package com.azkaisnandaru.farmerapp.model

data class LoginResponse(
    val error: Boolean,
    val loginResult: LoginResult?,
    val message: String
)
