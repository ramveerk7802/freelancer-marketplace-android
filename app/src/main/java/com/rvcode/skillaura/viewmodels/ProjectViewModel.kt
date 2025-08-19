package com.rvcode.skillaura.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rvcode.skillaura.models.project.ProjectResponse
import com.rvcode.skillaura.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(private val repository: ProjectRepository) : ViewModel(){

    private val TAG="PROJECTVIEWMODEL"

    private val _projects = MutableLiveData<List<ProjectResponse>>()
    val projects  : LiveData<List<ProjectResponse>> get() = _projects

    private val _isProgress = MutableLiveData<Boolean>(false)
    val isProgress: LiveData<Boolean> get() = _isProgress

    init {
        fetOpenProject()
    }


    fun fetOpenProject(){
        viewModelScope.launch {
            try {
                _isProgress.value = true
                val response = repository.getAllOpenProject()
                _projects.value=response
            }catch (e: Exception){
                Log.d(TAG,"Error In fetching Open All Project: ${e.message}")
            }finally {
                _isProgress.value = false
            }
        }
    }
}