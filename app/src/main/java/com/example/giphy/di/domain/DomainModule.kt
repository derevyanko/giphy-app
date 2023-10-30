package com.example.giphy.di.domain

import com.example.domain.repository.GiphyRepository
import com.example.domain.usecases.DeleteGiphyUseCase
import com.example.domain.usecases.GetGiphyBySearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun providesGetGiphyBySearchUseCase(
        repository: GiphyRepository
    ): GetGiphyBySearchUseCase =
        GetGiphyBySearchUseCase(repository)

    @Provides
    fun providesDeleteGiphyUseCase(
        repository: GiphyRepository
    ): DeleteGiphyUseCase =
        DeleteGiphyUseCase(repository)

    @Provides
    @Singleton
    fun providesCoroutineContext(): CoroutineContext = Job() + Dispatchers.IO
}