package com.sha.starzpaly.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sha.starzpaly.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(modifier: Modifier, goToMainScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            contentScale = ContentScale.Fit,
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "splash icon",

        )

    }

    LaunchedEffect(Unit) {
        delay(timeMillis = 2000)
        goToMainScreen.invoke()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplash() {
    SplashScreen(modifier = Modifier.fillMaxSize(), goToMainScreen = {})
}