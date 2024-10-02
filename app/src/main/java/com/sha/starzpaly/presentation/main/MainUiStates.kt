package com.sha.starzpaly.presentation.main

import com.sha.playdata.data.models.MediaSearchResponse

sealed interface MainUiStates {

    object Loading : MainUiStates
    object Idle : MainUiStates
    object Empty : MainUiStates
    class ErrorState(val message: String?) : MainUiStates
    class SuccessState(val data: MediaSearchResponse) : MainUiStates
}