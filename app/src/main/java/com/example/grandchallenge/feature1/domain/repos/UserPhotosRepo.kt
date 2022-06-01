package com.example.grandchallenge.feature1.domain.repos

import com.example.grandchallenge.feature1.domain.models.Photo
import com.example.grandchallenge.feature1.utils.Resource

interface UserPhotosRepo {
    suspend fun getUserPhotos(id: Int): kotlinx.coroutines.flow.Flow<Resource<List<Photo>>>
}