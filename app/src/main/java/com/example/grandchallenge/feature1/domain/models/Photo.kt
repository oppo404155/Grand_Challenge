package com.example.grandchallenge.feature1.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
    @PrimaryKey val roomId:Int?=null
)

