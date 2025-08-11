package com.rvcode.skillaura.util

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(@ApplicationContext private val context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        "secret_shared_pref",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )



    fun saveToken(token:String) = sharedPreferences.edit {
        this.putString("auth_token",token)
    }

    fun getToken():String? = sharedPreferences.getString("auth_token",null)

    fun clearToken()=sharedPreferences.edit().remove("auth_Token").apply()

    fun isLoggedIn():Boolean = !getToken().isNullOrEmpty()
}