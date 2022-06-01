package com.example.grandchallenge.feature1.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.grandchallenge.feature1.domain.models.Photo

@Dao
interface PhotosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotosList(photos: List<Photo>)

    @Query("select * from photo where albumId like:albumId")
    suspend fun getAlbumPhotos(albumId: Int): List<Photo>

    @Query("delete from photo")
    suspend fun clearPhotosFromCash()
}