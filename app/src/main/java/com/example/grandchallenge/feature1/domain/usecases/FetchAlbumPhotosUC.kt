package com.example.grandchallenge.feature1.domain.usecases

import com.example.grandchallenge.feature1.domain.repos.UserPhotosRepo
import com.example.grandchallenge.feature1.domain.repos.UserProfileRepo
import javax.inject.Inject

class FetchAlbumPhotosUC @Inject constructor(private val repo: UserPhotosRepo) {
    suspend operator fun invoke(albumId:Int)=repo.getUserPhotos(albumId)
}