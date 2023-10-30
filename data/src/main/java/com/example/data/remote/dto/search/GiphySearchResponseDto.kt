package com.example.data.remote.dto.search

data class GiphySearchResponseDto(
    val `data`: List<GiphySearchDto>,
    val meta: MetaDto,
    val pagination: PaginationDto
)