package com.example.ajarin.android.inbox.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.components.CommonHeader
import com.example.ajarin.android.inbox.presentation.components.InboxItem
import com.example.ajarin.inbox.presentation.InboxState

@Composable
fun InboxScreen(
    state: InboxState,
    onBackClick: () -> Unit,
    onMessageClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            CommonHeader(
                title = "Inbox",
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(
                items = state.inbox,
                key = { _, inbox -> inbox.inboxId }
            ) { index, inbox ->
                InboxItem(
                    modifier = Modifier
                        .clickable {
                            onMessageClick(inbox.mentor.id)
                        },
                    inbox = inbox
                )

                if (index != state.inbox.lastIndex) {
                    Divider(
                        startIndent = 66.dp
                    )
                }
            }
        }
    }
}