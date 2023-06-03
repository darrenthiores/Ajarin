package com.example.ajarin.android.message.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ajarin.android.R
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.home.presentation.Course
import com.example.ajarin.home.presentation.Mentor

@Composable
fun MessageHeader(
    mentor: Mentor?,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Rounded.ChevronLeft,
                    contentDescription = "Back"
                )
            }

            AsyncImage(
                modifier = modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                model = ImageRequest
                    .Builder(context)
                    .data(mentor?.photoUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_no_picture),
                contentDescription = mentor?.id + " photo"
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = mentor?.name ?: "loading...",
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Preview
@Composable
fun CommonHeaderPreview() {
    AjarinTheme {
        MessageHeader(
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
            ),
            onBackClick = {  }
        )
    }
}