package com.example.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.local.entities.GiphySearchDbEntity

@Dao
interface GiphySearchDao {

    @Query("SELECT * FROM giphy_search WHERE search = :search")
    fun get(search: String): PagingSource<Int, GiphySearchDbEntity>

    @Insert(entity = GiphySearchDbEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun save(giphy: List<GiphySearchDbEntity>)

    @Query("DELETE FROM giphy_search WHERE id = :id")
    fun delete(id: String)

    @Query("DELETE FROM giphy_search WHERE search = :search")
    fun clear(search: String)

    @Transaction
    fun refresh(search: String, gifs: List<GiphySearchDbEntity>) {
        clear(search)
        save(gifs)
    }
}