package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.models.search.GiphySearch
import kotlinx.coroutines.flow.Flow

interface GiphyRepository {

    suspend fun getGiphyBySearch(search: String): Flow<PagingData<GiphySearch>>

    suspend fun deleteGiphy(item: GiphySearch)
}