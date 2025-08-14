package com.rvcode.skillaura.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rvcode.skillaura.models.auth.LoginRequest
import com.rvcode.skillaura.models.auth.RegisterRequest
import com.rvcode.skillaura.repository.AuthRepository
import com.rvcode.skillaura.util.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val tokenManager: TokenManager,private val repository: AuthRepository) : ViewModel(){

    private val _data = MutableLiveData<String?>()
    val data : LiveData<String?> get() = _data

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isLoggedIn = MutableLiveData(false);
    val isLoggedIn :LiveData<Boolean> get() = _isLoggedIn


    init {
        viewModelScope.launch {
            val result = tokenManager.isLoggedIn()
            _isLoggedIn.value=result;
        }
    }

    fun registerUser(request: RegisterRequest,onSuccess:()-> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val result = repository.registerUser(request)
            if(result){
                _isLoading.postValue(false)
                withContext(Dispatchers.Main){
                    onSuccess()
                }

            }
        }
    }

    fun loginUser(request: LoginRequest,onSuccess: () -> Unit, onFailure:(String)-> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)

            try {
                val result = repository.loginUser(request)
                if(result!=null){
                    tokenManager.saveToken(token = result.token, role = result.role)
                    _isLoading.postValue(false)
                    withContext(Dispatchers.Main){
                        onSuccess()
                    }
                }else{
                    _isLoading.postValue(false)
                    withContext(Dispatchers.Main){
                        onFailure("Invalid credential.")
                    }
                }
            }catch (e: Exception){
                _isLoading.postValue(false)
            }

        }
    }


}