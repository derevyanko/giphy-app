package com.example.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "giphy_search")
data class GiphySearchDbEntity(
    @PrimaryKey val id: String,
    val url: String,
    val search: String
)
