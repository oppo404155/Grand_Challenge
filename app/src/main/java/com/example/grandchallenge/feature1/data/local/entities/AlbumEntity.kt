package com.example.grandchallenge.feature1.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlbumEntity(
    val userId:Int,
    val albumId: Int,
    val title: String,
    @PrimaryKey val id:Int?=null

)
