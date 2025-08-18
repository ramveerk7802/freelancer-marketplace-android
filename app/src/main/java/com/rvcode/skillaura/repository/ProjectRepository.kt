package com.rvcode.skillaura.repository

import com.rvcode.skillaura.apiservices.ProjectApi
import com.rvcode.skillaura.models.project.ProjectResponse
import javax.inject.Inject

class ProjectRepository @Inject constructor(private val projectApi: ProjectApi) {


    suspend fun getAllOpenProject(): List<ProjectResponse>?{
        return try {
            val response = projectApi.getAllOpenProjects()
            if(response.isSuccessful) {
                response.body()
            }
            else
                null
        }catch (e: Exception){
            null
        }
    }
}