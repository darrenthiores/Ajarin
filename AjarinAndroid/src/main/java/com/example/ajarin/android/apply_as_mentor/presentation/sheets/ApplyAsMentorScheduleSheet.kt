package com.example.ajarin.android.apply_as_mentor.presentation.sheets

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
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.components.CommonHeader
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.domain.order.model.Session
import com.example.ajarin.domain.order.model.sessions

@Composable
fun ApplyAsMentorScheduleSheet(
    modifier: Modifier = Modifier,
    selectedSchedules: List<Session>,
    onClick: (Session) -> Unit,
    onBackClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            CommonHeader(
                title = "Select Schedule",
                onBackClick = onBackClick
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        itemsIndexed(
            items = sessions
        ) { index, session ->
            ScheduleItem(
                modifier = Modifier
                    .toggleable(
                        value = selectedSchedules.contains(session),
                        role = Role.Checkbox,
                        onValueChange = {
                            onClick(session)
                        }
                    ),
                session = session,
                isChecked = selectedSchedules.contains(session)
            )

            if (index != sessions.lastIndex) {
                Divider()
            }
        }
    }
}

@Preview
@Composable
private fun ApplyAsMentorScheduleSheetPreview() {
    AjarinTheme {
        ApplyAsMentorScheduleSheet(
            selectedSchedules = emptyList(),
            onClick = {  },
            onBackClick = {  }
        )
    }
}

@Composable
private fun ScheduleItem(
    modifier: Modifier = Modifier,
    session: Session,
    isChecked: Boolean
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Session ${session.id}"
        )

        Text(
            text = ":"
        )

        Text(
            text = session.time
        )

        Checkbox(
            checked = isChecked,
            onCheckedChange = null
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
            isChecked = true
        )
    }
}