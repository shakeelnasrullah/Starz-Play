package com.sha.starzpaly.presentation.main

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sha.playdata.data.models.Media
import com.sha.playdata.data.models.onEmpty
import com.sha.playdata.data.models.onError
import com.sha.playdata.data.models.onLoading
import com.sha.playdata.data.models.onSuccess
import com.sha.playdata.domain.usecase.Params
import com.sha.playdata.domain.usecase.SearchMediaUseCase
import com.sha.playdata.language.LanguageHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MediaTypeList(val mediaType : String, val mediaList : List<Media>)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: SearchMediaUseCase,
    private val languageHelper: LanguageHelper
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiStates>(MainUiStates.Idle)
    val uiState: StateFlow<MainUiStates> = _uiState.asStateFlow()

    fun onEvent(event: MainEvents) {
        when (event) {
            is MainEvents.DoSearchMedia -> {
                doSearchMedia(query = event.query, activity = event.activity)
            }

            is MainEvents.ChangeLanguage -> {}
        }
    }

    fun filterMediaTypes(mediaList: List<Media>): ArrayList<String> {
        val mediaTypes = arrayListOf<String>()
        for (media in mediaList) {
            if (!mediaTypes.contains(media.mediaType)) {
                mediaTypes.add(media.mediaType)
            }
        }
        return mediaTypes
    }

    fun filterMediaTypesList(
        mediaList: List<Media>,
        mediaTypeList: ArrayList<String>
    ): ArrayList<MediaTypeList> {
        val listOfResponse = arrayListOf<MediaTypeList>()
        for (mediaType in mediaTypeList) {
            listOfResponse.add(
                MediaTypeList(
                    mediaType,
                    mediaList.filter { s -> s.mediaType == mediaType })
            )
        }
        return listOfResponse
    }

    private fun doSearchMedia(query: String, activity: Activity) {
        viewModelScope.launch {
            useCase.invoke(Params(query, languageHelper.getSelectedLanguage(activity)))
                .collect { dataResource ->
                    dataResource.onEmpty {
                        _uiState.value = MainUiStates.Empty
                    }
                    dataResource.onLoading {
                        _uiState.value = MainUiStates.Loading
                    }
                    dataResource.onSuccess {
                        _uiState.value = MainUiStates.SuccessState(this.data)
                    }
                    dataResource.onError {
                        _uiState.value = MainUiStates.ErrorState(this.exception.message)
                    }
                }
        }

    }
}