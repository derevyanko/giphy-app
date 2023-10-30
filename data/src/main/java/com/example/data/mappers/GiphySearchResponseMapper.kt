package com.example.data.mappers

import com.example.data.local.entities.GiphySearchDbEntity
import com.example.data.remote.dto.search.GiphySearchDto
import com.example.data.remote.dto.search.GiphySearchResponseDto
import com.example.data.remote.dto.search.ImagesDto
import com.example.data.remote.dto.search.PaginationDto
import com.example.domain.models.search.GiphySearch
import com.example.domain.models.search.GiphySearchResponse
import com.example.domain.models.search.Images
import com.example.domain.models.search.Pagination

object GiphySearchResponseMapper {

    object FromDtoToDb {

        operator fun invoke(
            from: GiphySearchResponseDto,
            search: String
        ) = from.data.map {
            it.toDbEntity(search)
        }

        private fun GiphySearchDto.toDbEntity(search: String) = GiphySearchDbEntity(
            id = this.id,
            url = this.images.original.url,
            search = search
        )
    }

    object FromDbToDomain {

        operator fun invoke(from: GiphySearchDbEntity) = GiphySearch(
            id = from.id,
            url = from.url,
        )
    }
}