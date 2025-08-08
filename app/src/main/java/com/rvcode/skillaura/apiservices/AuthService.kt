package com.rvcode.skillaura.apiservices

import com.rvcode.skillaura.models.TestResponse
import retrofit2.Response
import retrofit2.http.GET

interface AuthService {

    @GET("api/projects/test")
    suspend fun getData(): Response<TestResponse>
}