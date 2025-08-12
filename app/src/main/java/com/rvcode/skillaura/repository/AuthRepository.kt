package com.rvcode.skillaura.repository

import android.util.Log
import com.rvcode.skillaura.apiservices.AuthApi
import com.rvcode.skillaura.models.requests.LoginRequest
import com.rvcode.skillaura.models.requests.RegisterRequest
import com.rvcode.skillaura.models.response.AuthResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authApi: AuthApi) {

    suspend fun registerUser(request: RegisterRequest): Boolean{
        return try {
            val response = authApi.registerUser(request)
            response.isSuccessful

        }catch (e: Exception){
            Log.d("Auth","Failed to registration : ${e.message}")
            false
        }
    }

    suspend fun loginUser(request: LoginRequest): AuthResponse?{
        return try {
            val response = authApi.loginUser(request)
            if(response.isSuccessful ){
                response.body()
            }else{
                Log.e("Auth", "Login failed: ${response.code()} - ${response.message()}")
                null
            }
        }catch (e: Exception){
            Log.d("Auth","Login failed : ${e.message}")
            null
        }
    }
}