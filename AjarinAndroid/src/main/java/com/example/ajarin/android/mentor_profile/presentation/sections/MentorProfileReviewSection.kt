package com.example.ajarin.android.mentor_profile.presentation.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ajarin.android.R
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.domain.review.model.Review
import com.example.ajarin.domain.review.model.dummyReviews
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun MentorProfileReviewSection(
    modifier: Modifier = Modifier,
    reviews: List<Review>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        items(
            items = reviews,
            key = { review -> review.reviewId }
        ) { review ->
            ReviewItem(review = review)
        }
    }
}

@Preview
@Composable
private fun MentorProfileReviewSectionPreview() {
    AjarinTheme {
        MentorProfileReviewSection(reviews = dummyReviews)
    }
}

@Composable
private fun ReviewItem(
    modifier: Modifier = Modifier,
    review: Review
) {
    val context = LocalContext.current
    val width = LocalConfiguration.current.screenWidthDp / 2 - 32

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                model = ImageRequest
                    .Builder(context)
                    .data("https://fulaby-comment.oss-ap-southeast-5.aliyuncs.com/c1fbd653-29fe-4df3-8378-0afeb10b0e94-fulabyFeed.jpeg")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_no_picture),
                contentDescription = "profile photo"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                modifier = Modifier
                    .weight(1f),
                text = review.username,
                style = MaterialTheme.typography.subtitle2
            )

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "(${review.rating})",
                    style = MaterialTheme.typography.caption
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "rating",
                    tint = Color.Yellow
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Divider()

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            mainAxisSpacing = 4.dp,
            crossAxisSpacing = 4.dp
        ) {
            review.imagesUrl.forEach { url ->
                AsyncImage(
                    modifier = modifier
                        .size(width.dp),
                    contentScale = ContentScale.Crop,
                    model = ImageRequest
                        .Builder(context)
                        .data(url)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.ic_no_picture),
                    contentDescription = review.comment
                )
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = review.comment
        )

        Spacer(modifier = Modifier.height(8.dp))

        Divider()

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Reviewed at: ${review.reviewDate}",
            style = MaterialTheme.typography.body2
        )

        Text(
            text = "Review Session: ${review.sessionId}",
            style = MaterialTheme.typography.body2
        )
    }
}

@Preview
@Composable
private fun ReviewItemPreview() {
    AjarinTheme {
        ReviewItem(
            review = dummyReviews[0]
        )
    }
}