package com.rvcode.skillaura.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rvcode.skillaura.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel(){


    private val _data = MutableLiveData<String?>()
    val data : LiveData<String?> get() = _data

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getTestData()
            _data.postValue(result)

        }
    }


}