package com.sha.playdata.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sha.playdata.data.models.Media

@Dao
interface MediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedia(media: List<Media>)

    @Query("select * from media")
    fun getMediaList() : LiveData<List<Media>>
}