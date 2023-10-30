package com.example.data.local.datasource

import androidx.paging.PagingSource
import com.example.data.local.entities.DeletedGiphyIdEntity
import com.example.data.local.entities.GiphySearchDbEntity

interface GiphyLocalDataSource {

    fun getGiphyBySearch(search: String): PagingSource<Int, GiphySearchDbEntity>

    fun saveGiphyBySearch(giphy: List<GiphySearchDbEntity>)

    fun deleteGiphy(id: String)

    fun getDeletedGiphy(): List<DeletedGiphyIdEntity>

    fun addDeletedGiphy(id: DeletedGiphyIdEntity)
}