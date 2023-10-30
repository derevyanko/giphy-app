package com.example.giphy.di.data

import android.content.Context
import androidx.room.Room
import com.example.data.local.dao.DeletedGiphyDao
import com.example.data.local.dao.GiphySearchDao
import com.example.data.local.database.GiphyDatabase
import com.example.data.local.datasource.GiphyLocalDataSource
import com.example.data.local.datasource.GiphyLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun providesGiphyLocalDataSource(
        searchDao: GiphySearchDao,
        deletedDao: DeletedGiphyDao
    ): GiphyLocalDataSource =
        GiphyLocalDataSourceImpl(searchDao, deletedDao)

    @Provides
    @Singleton
    fun providesGiphyRoomDatabase(@ApplicationContext applicationContext: Context): GiphyDatabase =
        Room
            .databaseBuilder(applicationContext, GiphyDatabase::class.java, "giphy_database.db")
            .build()

    @Provides
    @Singleton
    fun providesGiphySearchDao(appDatabase: GiphyDatabase) = appDatabase.getGiphySearchDao()

    @Provides
    @Singleton
    fun providesDeletedGiphyDao(appDatabase: GiphyDatabase) = appDatabase.getDeletedGiphyDao()
}