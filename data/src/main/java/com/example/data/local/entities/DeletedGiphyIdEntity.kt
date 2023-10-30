package com.example.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deleted_giphy")
data class DeletedGiphyIdEntity(
    @PrimaryKey val id: String
)
