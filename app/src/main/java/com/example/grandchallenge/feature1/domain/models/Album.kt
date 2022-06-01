package com.example.grandchallenge.feature1.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Album(
    val userId: Int,
    val id: Int,
    val title: String,
    @PrimaryKey val roomId:Int?=null
) : Parcelable
