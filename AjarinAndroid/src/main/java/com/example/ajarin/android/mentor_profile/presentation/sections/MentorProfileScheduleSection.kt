package com.example.ajarin.android.mentor_profile.presentation.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.presentation.mentorProfile.Session
import com.example.ajarin.presentation.mentorProfile.sessions
import java.util.Calendar

@Composable
fun MentorProfileScheduleSection(
    modifier: Modifier = Modifier
) {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val currentSession = Session.currentSession(currentHour)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = 5.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    sessions.forEachIndexed { i, session ->
                        ScheduleItem(
                            session = session,
                            isCurrent = session.id == currentSession
                        )

                        if (i != sessions.lastIndex) {
                            Divider()
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MentorProfileScheduleSectionPreview() {
    AjarinTheme {
        MentorProfileScheduleSection()
    }
}

@Composable
private fun ScheduleItem(
    modifier: Modifier = Modifier,
    session: Session,
    isCurrent: Boolean
) {
    val textColor = if (isCurrent) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Session ${session.id}",
            color = textColor
        )

        Text(
            text = ":",
            color = textColor
        )

        Text(
            text = session.time,
            color = textColor
        )
    }
}

@Preview
@Composable
private fun ScheduleItemPreview() {
    AjarinTheme {
        ScheduleItem(
            session = Session(
                id = "1",
                time = "08:00 - 09:30"
            ),
            isCurrent = true
        )
    }
}