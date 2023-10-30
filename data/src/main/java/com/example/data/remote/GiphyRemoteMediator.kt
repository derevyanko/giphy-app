package com.example.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.data.local.dao.DeletedGiphyDao
import com.example.data.local.dao.GiphySearchDao
import com.example.data.local.entities.GiphySearchDbEntity
import com.example.data.mappers.GiphySearchResponseMapper
import com.example.data.remote.base.NetworkState
import com.example.data.remote.datasource.GiphyRemoteDataSource
import com.example.data.remote.dto.search.GiphySearchResponseDto
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@OptIn(ExperimentalPagingApi::class)
class GiphySearchRemoteMediator @AssistedInject constructor(
    private val giphySearchDao: GiphySearchDao,
    private val deletedGiphyDao: DeletedGiphyDao,
    private val api: GiphyRemoteDataSource,
    @Assisted private val search: String
) : RemoteMediator<Int, GiphySearchDbEntity>() {

    private var pageIndex = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GiphySearchDbEntity>
    ): MediatorResult {
        pageIndex = getPageIndex(loadType) ?:
            return MediatorResult.Success(endOfPaginationReached = true)

        val limit = state.config.pageSize
        val offset = pageIndex * limit

        return when (val apiResult = getGiphy(offset)) {
            is NetworkState.Success -> handleSuccessState(
                state = apiResult,
                loadType = loadType,
                limit = limit
            )
            is NetworkState.Failure -> handleFailureState(apiResult)
            else -> MediatorResult.Error(unexpectedException)
        }
    }

    private fun getPageIndex(loadType: LoadType): Int? {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return null
            LoadType.APPEND -> ++pageIndex
        }
        return pageIndex
    }

    private suspend fun getGiphy(offset: Int): NetworkState<GiphySearchResponseDto> {
        val apiResult = if (search.isBlank()) api.getTrendingGiphy()
            else api.getGiphyBySearch(search, offset)

        return apiResult
    }

    private fun handleSuccessState(
        state: NetworkState.Success<GiphySearchResponseDto>,
        loadType: LoadType,
        limit: Int
    ): MediatorResult {
        val data = state.data ?: return MediatorResult.Error(unexpectedException)
        val gifs = GiphySearchResponseMapper.FromDtoToDb(data, search)
        val deletedGifs = deletedGiphyDao.get().map { it.id }

        val filteredGifs = gifs.filter { it.id !in deletedGifs }

        if (loadType == LoadType.REFRESH) {
            giphySearchDao.refresh(search, filteredGifs)
        } else {
            giphySearchDao.save(filteredGifs)
        }

        return MediatorResult.Success(
            endOfPaginationReached = filteredGifs.size < limit
        )
    }

    private fun handleFailureState(state: NetworkState.Failure<GiphySearchResponseDto>): MediatorResult {
        val exception = state.exception ?: return MediatorResult.Error(unexpectedException)
        return MediatorResult.Error(exception)
    }

    @AssistedFactory
    interface Factory {
        fun create(search: String): GiphySearchRemoteMediator
    }

    companion object {

        val unexpectedException = Throwable("Unexpected exception!")
    }
}