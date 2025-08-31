package com.rvcode.skillaura.models.user

data class FreelancerProfile(
    val bio: String,
    val certificates: List<String>,
    val headline: String,
    val id: Int,
    val location: String,
    val profilePictureUrl: Any,
    val skills: List<String>,
    val verified: Boolean,
    val yearOfExperience: Int
)