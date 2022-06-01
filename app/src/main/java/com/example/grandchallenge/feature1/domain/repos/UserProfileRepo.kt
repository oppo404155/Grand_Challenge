package com.example.grandchallenge.feature1.domain.repos

import android.provider.MediaStore
import com.example.grandchallenge.feature1.domain.models.Album
import com.example.grandchallenge.feature1.domain.models.Photo
import com.example.grandchallenge.feature1.domain.models.User
import com.example.grandchallenge.feature1.domain.models.UserInfoResponse
import com.example.grandchallenge.feature1.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UserProfileRepo {
    suspend fun getUsersInfo(userId: Int): Flow<Resource<UserInfoResponse>>


}