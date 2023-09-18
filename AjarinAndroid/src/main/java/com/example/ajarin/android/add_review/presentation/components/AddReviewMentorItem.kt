package com.example.ajarin.android.add_review.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.ajarin.presentation.history.HistorySession
import com.example.ajarin.presentation.history.dummyHistory

@Composable
fun AddReviewMentorItem(
    modifier: Modifier = Modifier,
    history: HistorySession?
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
                .padding(16.dp)
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                model = ImageRequest
                    .Builder(context)
                    .data(history?.mentorImgUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_no_picture),
                contentDescription = history?.mentorId + " photo"
            )

            Spacer(modifier = Modifier.width(32.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = history?.mentorName ?: "loading...",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Text(
                    text = history?.course?.name ?: "loading...",
                    style = MaterialTheme.typography.body2.copy(
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Rp. ${history?.totalPrice}",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun AddReviewMentorItem() {
    AjarinTheme {
        AddReviewMentorItem(history = dummyHistory[0])
    }
}