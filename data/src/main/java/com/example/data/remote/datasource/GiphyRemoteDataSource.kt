package com.example.data.remote.datasource

import com.example.data.remote.base.NetworkState
import com.example.data.remote.dto.search.GiphySearchResponseDto

interface GiphyRemoteDataSource {

    suspend fun getTrendingGiphy(): NetworkState<GiphySearchResponseDto>

    suspend fun getGiphyBySearch(search: String, offset: Int): NetworkState<GiphySearchResponseDto>
}