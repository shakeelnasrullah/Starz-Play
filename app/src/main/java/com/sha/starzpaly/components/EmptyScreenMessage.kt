package com.sha.starzpaly.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sha.starzpaly.R

@Composable
fun EmptyScreenMessage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(modifier = Modifier.padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.empty_screen_msg), color = Color.Gray)
    }
}

@Composable
fun EmptyResponseMessage(isError : Boolean) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = if(isError)stringResource(id = R.string.error_msg) else stringResource(id = R.string.empty_response_msg),
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}

