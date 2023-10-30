package com.example.domain.usecases

import com.example.domain.models.search.GiphySearch
import com.example.domain.repository.GiphyRepository
import javax.inject.Inject

class DeleteGiphyUseCase @Inject constructor(
    private val repository: GiphyRepository
) {

    suspend operator fun invoke(giphy: GiphySearch) = repository.deleteGiphy(giphy)
}