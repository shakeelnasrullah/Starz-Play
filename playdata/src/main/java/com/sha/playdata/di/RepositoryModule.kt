package com.sha.playdata.di

import com.sha.playdata.data.remote.ApiService
import com.sha.playdata.domain.repository.SearchRepository
import com.sha.playdata.domain.repository.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {


    @Singleton
    @Provides
    fun provideSearchRepository( apiService: ApiService): SearchRepository {
       return SearchRepositoryImpl(apiService = apiService)

    }



}