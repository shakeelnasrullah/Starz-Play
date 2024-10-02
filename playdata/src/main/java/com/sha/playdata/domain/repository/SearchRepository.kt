package com.sha.playdata.domain.repository

import com.sha.playdata.BuildConfig
import com.sha.playdata.data.models.DataResource
import com.sha.playdata.data.models.MediaSearchResponse
import com.sha.playdata.data.models.callApi
import com.sha.playdata.data.models.data
import com.sha.playdata.data.models.isEmptyResponse
import com.sha.playdata.data.models.succeeded
import com.sha.playdata.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SearchRepository {
    fun searchMedia(query: String, language: String): Flow<DataResource<MediaSearchResponse>>
}

class SearchRepositoryImpl(
    private val apiService: ApiService
) : SearchRepository {
    override fun searchMedia(
        query: String,
        language: String
    ): Flow<DataResource<MediaSearchResponse>> = flow {
        emit(DataResource.Loading)
        val result = callApi { apiService.searchMedia(BuildConfig.API_KEY, query, language) }
        if (result.data.isEmptyResponse()) {
            emit(DataResource.Empty)
        } else if (result.succeeded()) {
            emit(DataResource.Success(result.data!!))
        } else {
            emit(DataResource.Error<Throwable>(RuntimeException("Not Found")))
        }
    }

}