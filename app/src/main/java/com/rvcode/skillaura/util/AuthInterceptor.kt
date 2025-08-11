package com.rvcode.skillaura.util

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(private val tokenManager: TokenManager) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        tokenManager.getToken()?.let {
            request.addHeader("Authorization","Bearer $it")
        }
        return chain.proceed(request = request.build())


    }
}