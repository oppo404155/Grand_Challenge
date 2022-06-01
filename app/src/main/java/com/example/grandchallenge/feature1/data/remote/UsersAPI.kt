package com.example.grandchallenge.feature1.data.remote

import com.example.grandchallenge.feature1.domain.models.Album
import com.example.grandchallenge.feature1.domain.models.Photo
import com.example.grandchallenge.feature1.domain.models.User
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersAPI {
    @GET("/users")
    suspend fun getUsers(): List<User>

    @GET("/albums?")
    suspend fun getUserAlbums(@Query("userId") userId: Int): List<Album>

    @GET("/photos?")
    suspend fun getPhotosOfAlbum(@Query("albumId") albumId: Int): List<Photo>
}