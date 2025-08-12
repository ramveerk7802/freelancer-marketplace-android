package com.rvcode.skillaura.apiservices

import com.rvcode.skillaura.models.requests.RegisterRequest
import com.rvcode.skillaura.models.TestResponse
import com.rvcode.skillaura.models.requests.LoginRequest
import com.rvcode.skillaura.models.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("api/auth/register")
    suspend fun registerUser(@Body request: RegisterRequest): Response<Void>

    @POST("api/auth/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<AuthResponse>
}