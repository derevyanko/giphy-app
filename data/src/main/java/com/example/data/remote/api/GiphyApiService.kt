package com.example.data.remote.api

import com.example.data.remote.dto.search.GiphySearchResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApiService {

    @GET("/v1/gifs/trending")
    suspend fun getTrendingGiphy(): Response<GiphySearchResponseDto>

    @GET("v1/gifs/search")
    suspend fun getGiphyBySearch(
        @Query("q") search: String,
        @Query("offset") offset: Int
    ): Response<GiphySearchResponseDto>
}