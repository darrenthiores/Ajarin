package com.example.ajarin.android.message.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme

@Composable
fun PersonalMessageItem(
    modifier: Modifier = Modifier,
    text: String
) {
    val width = (LocalConfiguration.current.screenWidthDp * 0.75) - 32

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier
                .widthIn(
                    min = 0.dp,
                    max = width.dp
                )
                .background(
                    color = MaterialTheme.colors.primary.copy(
                        alpha = 0.5f
                    ),
                    shape = RoundedCornerShape(
                        topStart = 8.dp,
                        bottomStart = 8.dp,
                        bottomEnd = 8.dp
                    )
                )
                .padding(8.dp),
            text = text,
            style = MaterialTheme.typography.caption
        )
    }
}

@Preview
@Composable
private fun PersonalMessageItemPreview() {
    AjarinTheme {
        PersonalMessageItem(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sodales rhoncus enim in lobortis. Sed ex est, ultricies vel tempor vel, sagittis eu massa. Ut placerat, turpis vel imperdiet aliquet, lacus lectus aliquam odio, ut placerat nibh nibh eu ex. Phasellus gravida elit et mattis posuere. Cras ut ornare turpis. In nec tristique mauris, id rhoncus turpis. Duis neque odio, porta vitae felis eget, pharetra fringilla nisl. Praesent congue lacus erat, eu pharetra nulla volutpat a")
    }
}

@Composable
fun FriendMessageItem(
    modifier: Modifier = Modifier,
    text: String
) {
    val width = (LocalConfiguration.current.screenWidthDp * 0.75) - 32

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .widthIn(
                    min = 0.dp,
                    max = width.dp
                )
                .background(
                    color = MaterialTheme.colors.secondary.copy(
                        alpha = 0.5f
                    ),
                    shape = RoundedCornerShape(
                        topEnd = 8.dp,
                        bottomStart = 8.dp,
                        bottomEnd = 8.dp
                    )
                )
                .padding(8.dp),
            text = text,
            style = MaterialTheme.typography.caption
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
private fun FriendMessageItemPreview() {
    AjarinTheme {
        FriendMessageItem(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sodales rhoncus enim in lobortis. Sed ex est, ultricies vel tempor vel, sagittis eu massa. Ut placerat, turpis vel imperdiet aliquet, lacus lectus aliquam odio, ut placerat nibh nibh eu ex. Phasellus gravida elit et mattis posuere. Cras ut ornare turpis. In nec tristique mauris, id rhoncus turpis. Duis neque odio, porta vitae felis eget, pharetra fringilla nisl. Praesent congue lacus erat, eu pharetra nulla volutpat a")
    }
}