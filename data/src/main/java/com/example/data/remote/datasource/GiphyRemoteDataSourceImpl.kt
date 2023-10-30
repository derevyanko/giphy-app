package com.example.data.remote.datasource

import com.example.data.remote.api.GiphyApiService
import com.example.data.remote.base.BaseRemoteDataSource
import javax.inject.Inject

class GiphyRemoteDataSourceImpl @Inject constructor(
    private val api: GiphyApiService
): BaseRemoteDataSource(), GiphyRemoteDataSource {

    override suspend fun getTrendingGiphy() = request { api.getTrendingGiphy() }

    override suspend fun getGiphyBySearch(search: String, offset: Int) = request {
        api.getGiphyBySearch(search, offset)
    }
}