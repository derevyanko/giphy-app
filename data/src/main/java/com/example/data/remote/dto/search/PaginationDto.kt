package com.example.data.remote.dto.search

import com.google.gson.annotations.SerializedName

data class PaginationDto(
    val count: Int,
    val offset: Int,

    @SerializedName("total_count") val totalCount: Int
)