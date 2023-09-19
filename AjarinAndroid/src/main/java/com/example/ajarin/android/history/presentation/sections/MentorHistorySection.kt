package com.example.ajarin.android.history.presentation.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ajarin.presentation.history.HistoryState

@Composable
fun MentorHistorySection(
    modifier: Modifier = Modifier,
    state: HistoryState,
    onItemClick: (String, String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
//        items(
//            items = state.historySessions,
//            key = { history -> history.id }
//        ) { history ->
//            MentorHistoryItem(
//                history = history,
//                onItemClick = {
//                    onItemClick(history.id, history.userId)
//                }
//            )
//        }
    }
}