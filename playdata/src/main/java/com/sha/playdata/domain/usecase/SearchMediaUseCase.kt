package com.sha.playdata.domain.usecase

import com.sha.playdata.data.models.DataResource
import com.sha.playdata.data.models.MediaSearchResponse
import com.sha.playdata.domain.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

data class Params(val query: String, val language: String)

class SearchMediaUseCase(
    private val repository: SearchRepository,
    dispatcher: CoroutineDispatcher
) : FlowUseCase<Params, DataResource<MediaSearchResponse>>(dispatcher) {
    override fun execute(parameters: Params): Flow<DataResource<MediaSearchResponse>> {
        return repository.searchMedia(query = parameters.query, language = parameters.language)
    }

}