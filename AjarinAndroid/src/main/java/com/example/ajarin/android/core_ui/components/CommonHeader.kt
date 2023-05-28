package com.example.ajarin.android.core_ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.ajarin.android.core_ui.theme.AjarinTheme

@Composable
fun CommonHeader(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Rounded.ChevronLeft,
                    contentDescription = "Back"
                )
            }

            Text(
                text = title,
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun CommonHeaderPreview() {
    AjarinTheme {
        CommonHeader(
            title = "First Aid",
            onBackClick = {  }
        )
    }
}