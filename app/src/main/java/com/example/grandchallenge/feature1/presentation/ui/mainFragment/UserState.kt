package com.example.grandchallenge.feature1.presentation.ui.mainFragment

import com.example.grandchallenge.feature1.domain.models.Album
import com.example.grandchallenge.feature1.domain.models.User
import java.util.logging.ErrorManager

data class UserState(
    val user: User?=null,
    val albums: List<Album> = emptyList(),
    val isLoading: Boolean? = false,
    val errorManager: String? = null
)
