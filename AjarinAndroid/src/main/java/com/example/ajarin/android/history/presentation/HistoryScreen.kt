package com.example.ajarin.android.history.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.components.CommonHeader
import com.example.ajarin.android.history.presentation.components.HistoryItem
import com.example.ajarin.history.presentation.HistoryState

@Composable
fun HistoryScreen(
    state: HistoryState,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CommonHeader(
                title = "History",
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = state.historySessions,
                key = { history -> history.id }
            ) { history ->
                HistoryItem(
                    history = history,
                    onItemClick = {

                    }
                )
            }
        }
    }
}