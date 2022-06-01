package com.example.grandchallenge.feature1.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.grandchallenge.feature1.data.local.entities.AlbumEntity
import com.example.grandchallenge.feature1.data.local.entities.UserEntity
import com.example.grandchallenge.feature1.domain.models.Album
import com.example.grandchallenge.feature1.domain.models.User

@Dao
interface UsersDAO {
    @Query("select * from User")
    suspend fun getUsersFromCash(): List<User>

    @Query("delete from User")
    suspend fun clearUserEntityCash()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsersToCash(User: List<User>)

}