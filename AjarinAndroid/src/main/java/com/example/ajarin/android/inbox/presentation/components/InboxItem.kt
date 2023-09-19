package com.example.ajarin.android.inbox.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ajarin.android.R
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.domain.core.model.Course
import com.example.ajarin.domain.mentor.model.Mentor
import com.example.ajarin.presentation.inbox.model.UiMessage

@Composable
fun InboxItem(
    modifier: Modifier = Modifier,
    inbox: UiMessage
) {
    val context = LocalContext.current
    val message = inbox.lastMessage
    val mentor = inbox.mentor

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = modifier
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            model = ImageRequest
                .Builder(context)
                .data(mentor.photoUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_no_picture),
            contentDescription = mentor.id + " photo"
        )

        Spacer(modifier = Modifier.width(32.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = mentor.name,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )

            Text(
                text = message,
                style = MaterialTheme.typography.body2.copy(
                    color = Color.Gray
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
private fun InboxItemPreview() {
    AjarinTheme {
        InboxItem(
            inbox = UiMessage(
                inboxId = "0",
                lastMessage = "Hello world!",
                mentor = Mentor(
                    id = "0",
                    photoUrl = "https://fulaby-feed-thumbnail.oss-ap-southeast-5.aliyuncs.com/05aa10b1-7786-4c6f-8cb1-1db90a74539d-fulabyFeedThumbnail.jpeg",
                    name = "Jonni",
                    education = "SMA 2",
                    rating = "4.5",
                    courses = listOf(
                        Course(
                            id = "0",
                            name = "Matematika"
                        )
                    ),
                    price = "15.000",
                    priceCategory = "<50k"
                )
            )
        )
    }
}