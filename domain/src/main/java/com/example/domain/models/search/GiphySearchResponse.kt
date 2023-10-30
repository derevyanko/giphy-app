package com.example.domain.models.search

data class GiphySearchResponse(
    val gifs: List<GiphySearch>,
    val pagination: Pagination
)
