package com.sha.starzpaly.presentation.main

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.CarouselDefaults
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.sha.playdata.BuildConfig
import com.sha.playdata.data.models.Media
import com.sha.starzpaly.R
import com.sha.starzpaly.components.EmptyResponseMessage
import com.sha.starzpaly.components.EmptyScreenMessage
import com.sha.starzpaly.components.LoadingView
import com.sha.starzpaly.components.SearchTextField

@Composable
fun MainScreen(
    modifier: Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    goToDetailScree: (Media) -> Unit
) {

    val activity = LocalContext.current as Activity
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Row for search text field and icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            SearchTextField(
                modifier = Modifier
                    .weight(0.9f), viewModel, activity
            )
            IconButton(
                onClick = { /* Handle search action */ },
                modifier = Modifier
                    .weight(0.1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = null
                )
            }
        }

        // Conditional rendering based on uiState
        when (uiState) {
            is MainUiStates.Idle -> {
                EmptyScreenMessage()
            }

            is MainUiStates.Loading -> {
                LoadingView()
            }

            is MainUiStates.SuccessState -> {
                val data = (uiState as MainUiStates.SuccessState).data.results
                val mediaList =
                    viewModel.filterMediaTypesList(data, viewModel.filterMediaTypes((data)))

                // Show the CarousalListView below the Row
                CarousalListView(modifier = modifier, mediaList, goToDetailScree)
            }

            is MainUiStates.ErrorState -> {
                // Handle error state
            }

            is MainUiStates.Empty -> {
                EmptyResponseMessage()
            }
        }
    }
}

@Composable
fun CarousalListView(
    modifier: Modifier,
    mediaList: ArrayList<MediaTypeList>,
    goToDetailScree: (Media) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize() // Make sure it fills the available space
    ) {
        items(mediaList.size) { index ->
            Text(
                text = mediaList[index].mediaType,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            ItemView(item = mediaList[index], goToDetailScree)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemView(item: MediaTypeList, goToDetailScree: (Media) -> Unit) {
    val pagerState = rememberCarouselState {
        item.mediaList.size
    }
    Column(modifier = Modifier.padding(8.dp)) {

        HorizontalUncontainedCarousel(
            state = pagerState, itemWidth = 120.dp,
            itemSpacing = 12.dp,
            contentPadding = PaddingValues(start = 12.dp),
            flingBehavior = CarouselDefaults.singleAdvanceFlingBehavior(state = pagerState),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 12.dp, bottom = 12.dp)
        ) { page ->

            val media = item.mediaList[page]
            Card(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(4.dp)
                    .clickable { goToDetailScree(media) },
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                /*var imagePath = ""

                if (media.profilePath != null) {
                    imagePath = media.profilePath.toString()
                } else {
                    imagePath = media.posterPath.toString()
                }*/
                AsyncImage(
                    model = BuildConfig.IMAGE_BASE_URL + if(media.posterPath == null)  media.profilePath else media.posterPath,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(modifier = Modifier.fillMaxSize(), goToDetailScree = {})
}

