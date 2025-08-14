package com.rvcode.skillaura.viewmodels;


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rvcode.skillaura.repository.UserRepository
import com.rvcode.skillaura.util.TokenManager
import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.launch

@HiltViewModel
class UserViewModel @Inject constructor(private val tokenManager: TokenManager,private val repository: UserRepository): ViewModel()   {

    private val _role = MutableLiveData<String>()
    val role: LiveData<String> get() = _role

    init {
        viewModelScope.launch {
            val dbRole = tokenManager.getRole()
            dbRole?.let {
                _role.value = it
            }
        }
    }
}
