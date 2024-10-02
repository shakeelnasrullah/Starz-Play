package com.sha.playdata.di

import com.sha.playdata.language.LanguageHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class LanguageModule {


    @Singleton
    @Provides
    fun provideLanguage(): LanguageHelper {
       return LanguageHelper()

    }



}