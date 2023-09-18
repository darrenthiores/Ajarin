package com.example.ajarin.android.booking.presentation.sheets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.components.CommonHeader
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.presentation.mentorProfile.Session
import com.example.ajarin.presentation.mentorProfile.sessions
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.util.Calendar

@Composable
fun BookingScheduleSheet(
    modifier: Modifier = Modifier,
    pickedDate: LocalDate?,
    onClick: (Session) -> Unit,
    onBackClick: () -> Unit
) {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val currentSession = Session.currentSession(currentHour)
    val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    val date = pickedDate ?: LocalDate(
        year = currentDate.year,
        month = currentDate.month,
        dayOfMonth = currentDate.dayOfMonth
    )
    val isToday = date.dayOfYear == currentDate.dayOfYear

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            CommonHeader(
                title = "Pick Schedule",
                onBackClick = onBackClick
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        itemsIndexed(
            items = sessions
        ) { index, session ->
            val disabled = isToday && (session.id.toIntOrNull() ?: 0) <= (currentSession.toIntOrNull() ?: 0)

            ScheduleItem(
                modifier = Modifier
                    .clickable {
                        if (!disabled) {
                            onClick(session)
                        }
                    },
                session = session,
                isDisabled = disabled
            )

            if (index != sessions.lastIndex) {
                Divider()
            }
        }
    }
}

@Preview
@Composable
private fun BookingScheduleSheetPreview() {
    AjarinTheme {
        BookingScheduleSheet(
            pickedDate = null,
            onClick = {  },
            onBackClick = {  }
        )
    }
}

@Composable
private fun ScheduleItem(
    modifier: Modifier = Modifier,
    session: Session,
    isDisabled: Boolean
) {
    val textColor = if (isDisabled) Color.LightGray else MaterialTheme.colors.onBackground

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
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
            isDisabled = true
        )
    }
}