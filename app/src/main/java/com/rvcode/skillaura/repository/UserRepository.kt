package com.rvcode.skillaura.repository

import com.rvcode.skillaura.apiservices.UserApi
import com.rvcode.skillaura.models.user.RoleResponse
import javax.inject.Inject

class UserRepository @Inject constructor(private val userApi: UserApi) {

    suspend fun getRole(): RoleResponse?{
        return try {
            val response = userApi.getRole()
            if(response.isSuccessful){
                response.body()
            }else {
                null
            }
        }catch (e: Exception){
            null
        }
    }
}