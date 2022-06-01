package com.example.grandchallenge.feature1.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.grandchallenge.feature1.data.local.entities.AlbumEntity
import com.example.grandchallenge.feature1.domain.models.Album

@Dao
interface AlbumsDao {
    @Query("select * from Album where userId like:userId")
    suspend fun getAlbumsFromCash(userId:Int): List<Album>

    @Query("delete from Album")
    suspend fun clearAlbumsFromCash()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbumsToCash(albums:List<Album>)
}