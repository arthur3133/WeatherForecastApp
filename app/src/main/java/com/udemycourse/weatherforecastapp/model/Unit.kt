package com.udemycourse.weatherforecastapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unit_tbl")
data class Unit(
    @PrimaryKey
    val unit: String
)
