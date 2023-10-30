package com.example.giphy.di.data

import com.example.data.local.dao.GiphySearchDao
import com.example.data.remote.GiphySearchRemoteMediator
import com.example.data.repository.GiphyRepositoryImpl
import com.example.domain.repository.GiphyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindGiphyRepository(
        giphyRepository: GiphyRepositoryImpl,
    ): GiphyRepository
}