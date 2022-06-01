package com.example.grandchallenge.feature1.presentation.ui.mainFragment

import com.example.grandchallenge.feature1.domain.models.Album
import com.example.grandchallenge.feature1.domain.models.User
import java.util.logging.ErrorManager

data class UserState(
    var user: User?=null,
    var albums: List<Album>? = emptyList(),
    var isLoading: Boolean? = false,
    var errorMessge: String? = null
)
