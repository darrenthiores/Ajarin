package com.example.ajarin.android.add_review.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme

@Composable
fun AddReviewHeader(
    title: String,
    postEnabled: Boolean,
    onPostClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Rounded.ChevronLeft,
                    contentDescription = "Back"
                )
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h5.copy(
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        actions = {
            TextButton(
                onClick = onPostClick,
                enabled = postEnabled
            ) {
                Text(text = "Post")
            }
        }
    )
}

@Preview
@Composable
private fun AddReviewHeaderPreview() {
    AjarinTheme {
        AddReviewHeader(
            title = "First Aid",
            postEnabled = true,
            onPostClick = {  },
            onBackClick = {  }
        )
    }
}