package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entities.DeletedGiphyIdEntity

@Dao
interface DeletedGiphyDao {

    @Query("SELECT * FROM deleted_giphy")
    fun get(): List<DeletedGiphyIdEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(giphy: DeletedGiphyIdEntity)
}