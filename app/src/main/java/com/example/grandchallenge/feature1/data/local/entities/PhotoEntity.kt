package com.example.grandchallenge.feature1.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoEntity(
    val title: String,
    val url: String,
    val thumbnailURL: String,
    @PrimaryKey val id: Int? = null
)
