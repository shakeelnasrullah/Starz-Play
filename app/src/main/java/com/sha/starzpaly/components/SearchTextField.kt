package com.sha.starzpaly.components

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.sha.starzpaly.presentation.main.MainEvents
import com.sha.starzpaly.presentation.main.MainViewModel

@Composable
fun SearchTextField(modifier: Modifier, viewModel: MainViewModel, activity: Activity) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = modifier) {
        TextField(
            value = searchText,
            onValueChange = { newValue ->
                searchText = newValue
            },
            placeholder = { Text(text = "Search...") },
        )
        IconButton(
            onClick = {
                if (searchText.text.isNotEmpty()) {
                    viewModel.onEvent(MainEvents.DoSearchMedia(searchText.text, activity))
                    keyboardController?.hide()
                }
            },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
        ) {
            Icon(Icons.Filled.Search, contentDescription = "Search")
        }
    }
}