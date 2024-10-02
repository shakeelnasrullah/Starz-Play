package com.sha.starzpaly.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sha.playdata.BuildConfig
import com.sha.playdata.data.models.Media
import com.sha.starzpaly.R
import com.sha.starzpaly.components.DetailContent
import com.sha.starzpaly.components.PlayButton


@Composable
fun DetailScreen(modifier: Modifier, media: Media, goToPlayer : (Media) -> Unit) {
//fun DetailScreen(modifier: Modifier, goToPlayer: (Media) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.3f)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp)
                    .fillMaxWidth()
                    .weight(0.4f)
                    .clickable { },
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {

                AsyncImage(
                    model = BuildConfig.IMAGE_BASE_URL + if(media.posterPath == null)  media.profilePath else media.posterPath,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }



            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f)
                .fillMaxHeight()
                .padding(top = 16.dp, bottom = 16.dp, end = 16.dp)) {

                DetailContent(media = media)
               
                Spacer(modifier = Modifier.weight(1f))
                PlayButton(onClick = { goToPlayer.invoke(media) }, text = "Play")
            }

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.7f)
                .background(Color.White)
        ) {

            Text(modifier = Modifier.padding(16.dp), color = Color.Gray, text = if(media.overview == null) "Details are not found" else media.overview!!)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
   // DetailScreen(modifier = Modifier.fillMaxSize(), goToPlayer = {})
}