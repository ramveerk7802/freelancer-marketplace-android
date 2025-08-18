package com.rvcode.skillaura.apiservices

import com.rvcode.skillaura.models.project.ProjectResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProjectApi {

    @GET("api/projects")
    suspend fun getAllOpenProjects(): Response<List<ProjectResponse>>
}