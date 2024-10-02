package com.sha.starzpaly.navigation

import com.google.gson.Gson
import com.sha.playdata.data.models.Media
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Destinations(val route: String) {

    object Splash : Destinations("splash")
    object Main : Destinations("main")
    object Detail : Destinations("detail/{mediaJson}") {
        fun createRoute(media: Media): String {
            val mediaJson = Gson().toJson(media)
            val encodedMediaJson = URLEncoder.encode(mediaJson, StandardCharsets.UTF_8.toString())
            return "detail/$encodedMediaJson"
        }
    }

    object MediaPlayer : Destinations("mediaPlayer/{mediaJson}") {
        fun createRoute(media: Media): String {
            val mediaJson = Gson().toJson(media)
            val encodedMediaJson = URLEncoder.encode(mediaJson, StandardCharsets.UTF_8.toString())
            return "mediaPlayer/$encodedMediaJson"
        }
    }

}
