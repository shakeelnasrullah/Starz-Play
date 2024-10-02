package com.sha.starzpaly.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sha.playdata.data.models.Media

@Composable
fun DetailContent(media: Media) {

    var mediaName = ""
    var mediaOriginalName = ""
    var mediaCategory = ""
    var mediaDate = ""

    if (media.mediaType == "movie") {
        mediaName = media.title
        mediaOriginalName = media.originalTitle
        mediaDate = media.releaseDate
        mediaCategory = "Movie"
    } else {
        mediaName = media.name
        mediaOriginalName = media.originalName
    }
    if (media.mediaType == "person") {
        mediaCategory = "Person"
        mediaDate = media.knownForDepartment
    }
    if (media.mediaType == "tv") {
        mediaDate = media.firstAirDate
        mediaCategory = "TV Show"
    }

    Text(
        text = mediaName, maxLines = 2,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 18.sp,
    )
    Text(
        text = mediaOriginalName, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        fontWeight = FontWeight.SemiBold, color = Color.Black,
        fontSize = 16.sp, maxLines = 2
    )
    Text(
        text = mediaDate,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        fontWeight = FontWeight.Normal,
        color = Color.Black, fontSize = 16.sp,
    )
    Text(
        text = mediaCategory,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        fontWeight = FontWeight.Normal,
        color = Color.Black, fontSize = 16.sp,
    )


}