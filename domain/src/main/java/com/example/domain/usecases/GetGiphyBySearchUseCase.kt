package com.example.domain.usecases

import com.example.domain.repository.GiphyRepository
import javax.inject.Inject

class GetGiphyBySearchUseCase @Inject constructor(
    private val repository: GiphyRepository
) {

    suspend operator fun invoke(search: String) = repository.getGiphyBySearch(search)
}