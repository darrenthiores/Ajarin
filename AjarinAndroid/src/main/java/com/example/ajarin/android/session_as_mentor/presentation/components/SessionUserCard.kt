package com.example.ajarin.android.session_as_mentor.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.presentation.home.Course
import com.example.ajarin.presentation.profile.User
import com.example.ajarin.presentation.profile.dummyUsers

@Composable
fun SessionUserCard(
    modifier: Modifier = Modifier,
    user: User?,
    course: Course?
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
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
                        text = user?.name?.first()?.uppercase() ?: "?",
                        style = MaterialTheme.typography.subtitle1.copy(
                            color = MaterialTheme.colors.onPrimary
                        )
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = user?.name ?: "loading...",
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    Text(
                        text = user?.email ?: "loading...",
                        style = MaterialTheme.typography.body2.copy(
                            color = Color.Gray,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Course: ${course?.name ?: "loading..."}",
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Preview
@Composable
private fun SessionUserCardPreview() {
    AjarinTheme {
        SessionUserCard(
            user = dummyUsers[0],
            course = Course(
                id = "0",
                name = "Matematika"
            )
        )
    }
}