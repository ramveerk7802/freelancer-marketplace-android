package com.rvcode.skillaura.models.user

data class UserModel(
    val email: String,
    val freelancerProfile: FreelancerProfile,
    val id: Int,
    val name: String,
    val password: String,
    val role: String
)