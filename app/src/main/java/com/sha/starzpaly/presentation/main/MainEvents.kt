package com.sha.starzpaly.presentation.main

import android.app.Activity

sealed interface MainEvents{

    data class DoSearchMedia(val query : String, val activity: Activity) : MainEvents

    class ChangeLanguage(val language: String) : MainEvents
}