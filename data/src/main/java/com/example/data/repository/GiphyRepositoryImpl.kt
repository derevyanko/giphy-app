package com.example.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.local.dao.DeletedGiphyDao
import com.example.data.local.dao.GiphySearchDao
import com.example.data.local.datasource.GiphyLocalDataSource
import com.example.data.local.entities.DeletedGiphyIdEntity
import com.example.data.mappers.GiphySearchResponseMapper
import com.example.data.remote.GiphySearchRemoteMediator
import com.example.data.utils.GIPHY_SEARCH_PAGE_SIZE
import com.example.domain.models.search.GiphySearch
import com.example.domain.repository.GiphyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GiphyRepositoryImpl @Inject constructor(
    private val db: GiphyLocalDataSource,
    private val remoteMediatorFactory: GiphySearchRemoteMediator.Factory,
): GiphyRepository {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getGiphyBySearch(search: String): Flow<PagingData<GiphySearch>> {
        return Pager(
            config = PagingConfig(
                pageSize = GIPHY_SEARCH_PAGE_SIZE,
                initialLoadSize = GIPHY_SEARCH_PAGE_SIZE,
                prefetchDistance = 0
            ),
            remoteMediator = remoteMediatorFactory.create(search = search),
            pagingSourceFactory = { db.getGiphyBySearch(search) }
        )
            .flow
            .map { pagingData ->
                pagingData.map { giphyDbEntity ->
                    GiphySearchResponseMapper.FromDbToDomain(giphyDbEntity)
                }
            }
    }

    override suspend fun deleteGiphy(giphy: GiphySearch) {
        db.deleteGiphy(giphy.id)

        db.addDeletedGiphy(DeletedGiphyIdEntity(giphy.id))
    }
}