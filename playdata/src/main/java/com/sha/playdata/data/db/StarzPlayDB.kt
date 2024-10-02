package com.sha.playdata.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sha.playdata.data.models.Media
import com.sha.playdata.data.models.MediaSearchResponse

@Database(entities = [MediaSearchResponse::class, Media::class], version = 1)
@TypeConverters(MediaConverter::class, KnownForConverter::class, GenreConverter::class)
abstract class StarzPlayDB : RoomDatabase() {

    abstract fun getMediaDao(): MediaDao
}