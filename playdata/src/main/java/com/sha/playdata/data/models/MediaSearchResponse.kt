package com.sha.playdata.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "response")
data class MediaSearchResponse(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Media>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)