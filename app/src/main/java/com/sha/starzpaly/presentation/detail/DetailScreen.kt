package com.sha.starzpaly.presentation.detail

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import coil.compose.AsyncImage
import com.sha.playdata.BuildConfig
import com.sha.playdata.data.models.Media
import com.sha.starzpaly.R
import com.sha.starzpaly.components.ComposableLifecycle
import com.sha.starzpaly.components.DetailContent
import com.sha.starzpaly.components.PlayButton

@Composable
fun DetailScreen(modifier: Modifier, media: Media, goToPlayer: (Media) -> Unit) {

    val activity = LocalContext.current as Activity

    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {}
            Lifecycle.Event.ON_START -> {}
            Lifecycle.Event.ON_RESUME -> {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }

            Lifecycle.Event.ON_PAUSE -> {}
            Lifecycle.Event.ON_STOP -> {}
            Lifecycle.Event.ON_DESTROY -> {}
            Lifecycle.Event.ON_ANY -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {


        Box (modifier = Modifier
            .fillMaxSize()
            .weight(0.3f)) {
            AsyncImage(
                model = BuildConfig.IMAGE_BASE_URL + if (media.mediaType == "person") media.knownFor.get(
                    0
                ).backdropPath else media.backdropPath,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                contentDescription = "poster"
            )


        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.transparent))
        ) {


            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp)
                    .fillMaxWidth()
                    .weight(0.4f)
                    .clickable { },
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {

                AsyncImage(
                    model = BuildConfig.IMAGE_BASE_URL + if (media.posterPath == null) media.profilePath else media.posterPath,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f)
                    .fillMaxHeight()
                    .padding(top = 16.dp, bottom = 16.dp, end = 16.dp)
            ) {

                DetailContent(media = media)

                Spacer(modifier = Modifier.weight(1f))
                PlayButton(onClick = { goToPlayer.invoke(media) }, text = "Play")
            }
        }

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.7f)
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                modifier = Modifier.padding(16.dp),
                color = Color.Gray,
                text = if (media.overview == null) "Details are not found" else media.overview!!
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    // DetailScreen(modifier = Modifier.fillMaxSize(), goToPlayer = {})
}