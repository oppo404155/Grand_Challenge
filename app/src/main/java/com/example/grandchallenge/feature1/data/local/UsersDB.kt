package com.example.grandchallenge.feature1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.grandchallenge.feature1.data.local.dao.AlbumsDao
import com.example.grandchallenge.feature1.data.local.dao.PhotosDao
import com.example.grandchallenge.feature1.data.local.dao.UsersDAO
import com.example.grandchallenge.feature1.domain.models.Album
import com.example.grandchallenge.feature1.domain.models.Photo
import com.example.grandchallenge.feature1.domain.models.User

@Database(
    entities = arrayOf(User::class, Album::class, Photo::class),
    version = 3
)
abstract class UsersDB : RoomDatabase() {
    abstract fun getUserDAO(): UsersDAO
    abstract fun getAlbumDao(): AlbumsDao
    abstract fun getPhotosDao(): PhotosDao

}