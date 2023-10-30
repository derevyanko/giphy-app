package com.example.data.remote.dto.search

import com.google.gson.annotations.SerializedName

data class MetaDto(
    val msg: String,

    @SerializedName("response_id") val responseId: String,

    val status: Int
)