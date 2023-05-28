package com.example.ajarin.android.core_ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme

@Composable
fun NegativeConfirmationDialog(
    message: String,
    onDismiss: () -> Unit,
    onClicked: () -> Unit,
    cancellationText: String,
    confirmationText: String
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = { Text(text = message) },
        buttons = {
            Column {
                Divider()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(
                        onClick = onDismiss,
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        Text(
                            text = cancellationText
                        )
                    }

                    TextButton(
                        onClick = onClicked,
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        Text(
                            text = confirmationText,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun NegativeConfirmationDialogPreview() {
    AjarinTheme {
        NegativeConfirmationDialog(
            message = "Are you sure to log out?",
            onDismiss = {  },
            onClicked = {  },
            cancellationText = "Cancel",
            confirmationText = "Log Out"
        )
    }
}