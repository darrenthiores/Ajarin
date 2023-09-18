package com.example.ajarin.android.history.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ajarin.android.R
import com.example.ajarin.android.core_ui.components.PrimaryButton
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.presentation.history.HistorySession
import com.example.ajarin.presentation.history.dummyHistory
import com.example.ajarin.presentation.history.getStatusMessage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HistoryItem(
    modifier: Modifier = Modifier,
    history: HistorySession,
    onItemClick: () -> Unit,
    onReviewClick: () -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp),
        onClick = onItemClick
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
                    .data(history.mentorImgUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_no_picture),
                contentDescription = history.mentorId + " photo"
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(0.6f)
            ) {
                Text(
                    text = history.mentorName,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Text(
                    text = history.course.name,
                    style = MaterialTheme.typography.body2.copy(
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Rp. ${history.totalPrice}",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.4f),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = history.date.toString(),
                    style = MaterialTheme.typography.caption.copy(
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Text(
                    text = history.schedule.time,
                    style = MaterialTheme.typography.caption.copy(
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .requiredHeight(16.dp)
                )

                if (history.status == "3") {
                    PrimaryButton(text = "Review") {
                        onReviewClick()
                    }
                } else {
                    Text(
                        text = getStatusMessage(history.status),
                        style = MaterialTheme.typography.caption.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.End
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HistoryItemPreview() {
    AjarinTheme {
        HistoryItem(
            history = dummyHistory[0].copy(status = "3"),
            onItemClick = {  },
            onReviewClick = {  }
        )
    }
}