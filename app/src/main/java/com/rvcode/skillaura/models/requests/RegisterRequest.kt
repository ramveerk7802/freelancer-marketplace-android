package com.rvcode.skillaura.models.requests

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val role: String
)