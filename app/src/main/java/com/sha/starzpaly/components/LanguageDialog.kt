package com.sha.starzpaly.components

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.sha.playdata.language.LanguageHelper
import com.sha.playdata.utils.Utils
import com.sha.starzpaly.R
@Composable
fun LanguageDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onLanguageSelected: (String) -> Unit,
    languageHelper: LanguageHelper,
    selectedLanguageIndex: Int // Add this parameter
) {
    val context = LocalContext.current as Activity

    // Get the list of languages
    val (_, languages) = languageHelper.getLanguageList(context)

    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = stringResource(R.string.language_dialog_title),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            },
            text = {
                Column {
                    languages.forEachIndexed { index, language ->
                        Text(
                            text = language,
                            modifier = Modifier
                                .clickable {
                                    Utils.saveSelectedLanguage(context, language)
                                    onLanguageSelected(language)
                                    onDismiss()
                                }
                                .padding(8.dp),
                            color = if (index == selectedLanguageIndex) Color.Blue else Color.Black // Highlight selected
                        )
                    }
                }
            },
            confirmButton = {
                Button(onClick = onDismiss) {
                    Text("Close")
                }
            }
        )
    }
}
