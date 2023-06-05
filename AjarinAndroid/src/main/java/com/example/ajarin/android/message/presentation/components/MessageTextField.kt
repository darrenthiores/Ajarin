package com.example.ajarin.android.message.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme

@Composable
fun MessageTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit
) {
    BasicTextField(
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        modifier = modifier,
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colors.onSurface
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.surface.copy(
                            alpha = 0.5f
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 16.dp)
                    .heightIn(min = 40.dp, max = 120.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = "Message...",
                            style = LocalTextStyle.current.copy(
                                color = MaterialTheme.colors.onSurface.copy(
                                    alpha = 0.3f
                                )
                            )
                        )
                    }

                    innerTextField()
                }
            }
        }
    )
}

@Preview
@Composable
private fun MessageTextFieldPreview() {
    AjarinTheme(
        darkTheme = true
    ) {
        MessageTextField(
            text = "test",
            onTextChange = {  }
        )
    }
}