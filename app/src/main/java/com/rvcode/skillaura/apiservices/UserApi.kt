package com.rvcode.skillaura.apiservices

import com.rvcode.skillaura.models.user.RoleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface UserApi {

    @GET("api/users/role")
    suspend fun getRole(): Response<RoleResponse>

    @GET("api/users")
    suspend fun getUser(): Response<T>
}