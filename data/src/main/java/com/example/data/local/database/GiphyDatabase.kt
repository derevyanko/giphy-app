package com.example.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.DeletedGiphyDao
import com.example.data.local.dao.GiphySearchDao
import com.example.data.local.entities.DeletedGiphyIdEntity
import com.example.data.local.entities.GiphySearchDbEntity

@Database(
    version = 1,
    entities = [
        GiphySearchDbEntity::class,
        DeletedGiphyIdEntity::class
    ]
)
abstract class GiphyDatabase: RoomDatabase() {

    abstract fun getGiphySearchDao(): GiphySearchDao

    abstract fun getDeletedGiphyDao(): DeletedGiphyDao
}