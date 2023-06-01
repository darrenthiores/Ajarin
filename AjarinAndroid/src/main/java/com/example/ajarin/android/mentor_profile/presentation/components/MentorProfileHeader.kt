package com.example.ajarin.android.mentor_profile.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme

@Composable
fun MentorProfileHeader(
    title: String,
    onBackClick: () -> Unit,
    onChatClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
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
        actions = {
            IconButton(
                onClick = onChatClick,
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Rounded.Chat,
                    contentDescription = "Chat"
                )
            }

            IconButton(
                onClick = onShareClick,
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Rounded.Share,
                    contentDescription = "Share"
                )
            }
        }
    )
}

@Preview
@Composable
fun MentorProfileHeaderPreview() {
    AjarinTheme {
        MentorProfileHeader(
            title = "First Aid",
            onBackClick = {  },
            onChatClick = {  },
            onShareClick = {  }
        )
    }
}