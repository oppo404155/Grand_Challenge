package com.example.grandchallenge.feature1.domain.models

data class UserInfoResponse(
    val user:User,
    val albums:List<Album>
)
