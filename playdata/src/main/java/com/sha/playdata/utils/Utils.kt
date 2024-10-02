package com.sha.playdata.utils

import android.app.Activity
import android.content.Context

class Utils {

    companion object {
        fun saveSelectedLanguage(activity: Activity?, language: String) {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
            with(sharedPref.edit()) {
                putString(Constants.languageExtras, language)
                apply()
            }
        }

        fun getSelectedLanguage(activity: Activity?): String {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            val defaultValue = "English"
            val language = sharedPref?.getString(Constants.languageExtras, defaultValue)
            return language!!
        }
    }
}