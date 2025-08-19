package com.rvcode.skillaura.repository

import android.util.Log
import com.rvcode.skillaura.apiservices.ProjectApi
import com.rvcode.skillaura.models.project.ProjectResponse
import javax.inject.Inject

class ProjectRepository @Inject constructor(private val projectApi: ProjectApi) {


    suspend fun getAllOpenProject(): List<ProjectResponse>{
        val response = projectApi.getAllOpenProjects()
        return if(response.isSuccessful) {
//            Log.d("REPOSITORY",response.body().toString())
            response.body()?:emptyList()

        }else
            throw Exception("Api Error Code : ${response.code()} - - ${response.message()}")
    }
}