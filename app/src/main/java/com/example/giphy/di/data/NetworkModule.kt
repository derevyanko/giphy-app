package com.example.giphy.di.data

import com.example.data.remote.api.GiphyApiService
import com.example.data.remote.datasource.GiphyRemoteDataSource
import com.example.data.remote.datasource.GiphyRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesGiphyRemoteDataSource(
        api: GiphyApiService,
    ): GiphyRemoteDataSource = GiphyRemoteDataSourceImpl(
        api = api
    )
}