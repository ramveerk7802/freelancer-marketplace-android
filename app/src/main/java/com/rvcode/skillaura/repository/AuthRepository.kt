package com.rvcode.skillaura.repository

import android.util.Log
import com.rvcode.skillaura.apiservices.AuthApi
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authApi: AuthApi) {

    suspend fun getTestData(): String?{
        try {
            delay(2000)
            val response = authApi.getData()
            if(response.isSuccessful && response.body()!=null){
                val body =  response.body()
                return body?.message
            }else{
                return "Response not get"
            }

        }catch (e:Exception){
            Log.d("AUTH_SERVICE","Failed to fetch data from retrofit ${e.message}")
            throw e;
        }
        return "Without retrofit"

    }
}