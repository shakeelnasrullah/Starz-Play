package com.sha.playdata.di

import android.content.Context
import androidx.room.Room
import com.sha.playdata.data.db.StarzPlayDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context.applicationContext,
        klass = StarzPlayDB::class.java, name = "StarPlay"
    ).build()

    @Provides
    @Singleton
    fun providesRecordDao(database: StarzPlayDB) = database.getMediaDao()

}