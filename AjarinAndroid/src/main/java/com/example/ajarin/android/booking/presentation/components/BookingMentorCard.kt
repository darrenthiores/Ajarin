package com.example.ajarin.android.booking.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ajarin.android.R
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.presentation.home.Course
import com.example.ajarin.presentation.home.Mentor

@Composable
fun BookingMentorCard(
    modifier: Modifier = Modifier,
    mentor: Mentor?
) {
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
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

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = mentor?.name ?: "loading...",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Text(
                    text = mentor?.education ?: "loading...",
                    style = MaterialTheme.typography.body2.copy(
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            Text(
                text = "Rp. ${mentor?.price}",
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Preview
@Composable
private fun BookingMentorCardPreview() {
    AjarinTheme {
        BookingMentorCard(
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
    }
}