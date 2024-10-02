package com.sha.playdata.data.remote

import com.sha.playdata.BuildConfig
import com.sha.playdata.data.models.MediaSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/search/multi")
    suspend fun searchMedia(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("query") searchQuery: String,
        @Query("language") languageCode: String
    ): MediaSearchResponse
}