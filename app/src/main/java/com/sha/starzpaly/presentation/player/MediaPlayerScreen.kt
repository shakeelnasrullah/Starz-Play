package com.sha.starzpaly.presentation.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.sha.playdata.BuildConfig
import com.sha.playdata.data.models.Media
import com.sha.starzpaly.components.PlayButton

@Composable
fun MediaPlayerScreen(modifier: Modifier = Modifier, media: Media) {
    var isPlaying by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black) // For better video viewing experience
    ) {
        if (isPlaying) {
            VideoPlayer(url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
            // Display the poster image at the top left when video is playing
            AsyncImage(
                model = BuildConfig.IMAGE_BASE_URL + (media.posterPath ?: media.profilePath),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(100.dp, 60.dp) // Set the size of the poster
                    .align(Alignment.TopStart)
                    .clip(RoundedCornerShape(10.dp)) // Optional: round corners
            )
        } else {
            // Display poster image with play button
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = BuildConfig.IMAGE_BASE_URL + (media.posterPath ?: media.profilePath),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp)) // Optional: round corners
                        .fillMaxSize() // Fill the screen
                )
                // Play button
                Button(
                    onClick = {isPlaying = true},
                    modifier = Modifier.wrapContentWidth().padding(horizontal = 16.dp).align(Alignment.BottomCenter),
                    shape = RoundedCornerShape(6.dp), // Set the corner radius
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green) // Customize the button color
                ) {
                    Text(text = "Play", color = Color.White) // Button text
                }

            }
        }
    }
}

@Composable
fun VideoPlayer(url: String) {
    val context = LocalContext.current
    val player = remember { ExoPlayer.Builder(context).build() }

    val mediaItem = MediaItem.fromUri(url)
    player.setMediaItem(mediaItem)
    player.prepare()
    player.playWhenReady = true

    // Create a full-screen video view
    AndroidView(
        factory = { PlayerView(context).apply { this.player = player } },
        modifier = Modifier.fillMaxSize()
    )

    // Clean up the player when the Composable is disposed
    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }
}
