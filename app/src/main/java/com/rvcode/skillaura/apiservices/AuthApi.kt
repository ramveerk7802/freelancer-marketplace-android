package com.rvcode.skillaura.apiservices

import com.rvcode.skillaura.models.auth.RegisterRequest
import com.rvcode.skillaura.models.auth.LoginRequest
import com.rvcode.skillaura.models.auth.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/auth/register")
    suspend fun registerUser(@Body request: RegisterRequest): Response<Void>

    @POST("api/auth/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<AuthResponse>
}