package com.sha.playdata.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sha.playdata.data.models.KnownFor

class KnownForConverter {

    @TypeConverter
    fun fromList(value: List<KnownFor>): String {
        val gson = Gson()
        val type = object : TypeToken<List<KnownFor>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toList(value: String): List<KnownFor> {
        val gson = Gson()
        val type = object : TypeToken<List<KnownFor>>() {}.type
        return gson.fromJson(value, type)
    }
}