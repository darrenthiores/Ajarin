package com.example.ajarin.android.message.presentation

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
import com.example.ajarin.android.message.presentation.components.FriendMessageItem
import com.example.ajarin.android.message.presentation.components.MessageBottomBar
import com.example.ajarin.android.message.presentation.components.MessageHeader
import com.example.ajarin.android.message.presentation.components.PersonalMessageItem
import com.example.ajarin.presentation.message.MessageEvent
import com.example.ajarin.presentation.message.MessageState

@Composable
fun MessageScreen(
    state: MessageState,
    mentorId: String,
    onEvent: (MessageEvent) -> Unit,
    onSendMessage: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            MessageHeader(
                mentor = state.mentor,
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            MessageBottomBar(
                message = state.newMessage,
                onMessageChange = {
                    onEvent(
                        MessageEvent.OnMessageChange(it)
                    )
                },
                onSendClick = onSendMessage
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            reverseLayout = true
        ) {
            items(
                items = state.messages,
                key = { message -> message.id }
            ) { message ->
                if (message.sentToId == mentorId) {
                    PersonalMessageItem(text = message.content)
                } else {
                    FriendMessageItem(text = message.content)
                }
            }
        }
    }
}