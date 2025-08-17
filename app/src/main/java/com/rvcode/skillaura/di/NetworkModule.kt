package com.rvcode.skillaura.di

import com.rvcode.skillaura.apiservices.AuthApi
import com.rvcode.skillaura.apiservices.UserApi
import com.rvcode.skillaura.util.AuthInterceptor
import com.rvcode.skillaura.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesAuthApi(retrofitBuilder: Retrofit.Builder): AuthApi{
        return retrofitBuilder.build().create(AuthApi::class.java)
    }



    @Singleton
    @Provides
    fun providesUserApi(retrofitBuilder: Retrofit.Builder, client: OkHttpClient): UserApi{
        return  retrofitBuilder.client(client).build().create(UserApi::class.java)

    }
}