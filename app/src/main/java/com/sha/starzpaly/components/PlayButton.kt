package com.sha.starzpaly.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PlayButton(onClick : () -> Unit, text : String){
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        shape = RoundedCornerShape(6.dp), // Set the corner radius
        colors = ButtonDefaults.buttonColors(containerColor = Color.Green) // Customize the button color
    ) {
        Text(text = text, color = Color.White) // Button text
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButton(){
    PlayButton(onClick = {}, text = "Play")
}