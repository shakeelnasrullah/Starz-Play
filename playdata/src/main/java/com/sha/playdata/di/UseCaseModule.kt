package com.sha.playdata.di

import com.sha.playdata.domain.repository.SearchRepository
import com.sha.playdata.domain.usecase.SearchMediaUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {


    @Singleton
    @Provides
    fun provideSearchUseCase( repository: SearchRepository): SearchMediaUseCase {
       return SearchMediaUseCase(repository, dispatcher = Dispatchers.IO)

    }



}