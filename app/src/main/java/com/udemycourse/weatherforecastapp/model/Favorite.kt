package com.udemycourse.weatherforecastapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_tbl")
data class Favorite(
    @PrimaryKey
    val city: String,
    val country: String
)
