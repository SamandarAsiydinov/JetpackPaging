package com.example.jetpackpaging.model

data class UserResponse(
    val page: Int,
    val data: List<UserData>
)
data class UserData(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    var avatar: String
)