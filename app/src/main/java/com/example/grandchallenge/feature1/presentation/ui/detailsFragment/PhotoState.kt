package com.example.grandchallenge.feature1.presentation.ui.detailsFragment

import com.example.grandchallenge.feature1.domain.models.Photo

data class PhotoState(
    var isLoading: Boolean? = false,
    var photos: List<Photo>? = emptyList(),
    var errorMessage: String? = null
)
