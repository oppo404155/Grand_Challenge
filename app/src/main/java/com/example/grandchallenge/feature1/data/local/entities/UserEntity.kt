package com.example.grandchallenge.feature1.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.grandchallenge.feature1.domain.models.Address
@Entity
data class UserEntity(
    val UserID:Int,
    val name: String,
    val username: String,
  @Embedded  val address: com.example.grandchallenge.feature1.data.local.entities.Address,
    @PrimaryKey val id:Int?=null

)
data class Address(
    val street: String,
    val suite: String,
    val city: String,
)
