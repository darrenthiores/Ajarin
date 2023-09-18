package com.example.ajarin.android.history.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.presentation.history.HistorySession
import com.example.ajarin.presentation.history.dummyHistory
import com.example.ajarin.presentation.history.getMentorStatusMessage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MentorHistoryItem(
    modifier: Modifier = Modifier,
    history: HistorySession,
    onItemClick: () -> Unit
) {
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
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = history.userName.first().uppercase(),
                    style = MaterialTheme.typography.subtitle1.copy(
                        color = MaterialTheme.colors.onPrimary
                    )
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(0.6f)
            ) {
                Text(
                    text = history.userName,
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

                Text(
                    text = getMentorStatusMessage(history.status),
                    style = MaterialTheme.typography.caption.copy(
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.End
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun MentorHistoryItemPreview() {
    AjarinTheme {
        MentorHistoryItem(
            history = dummyHistory[0],
            onItemClick = {  }
        )
    }
}