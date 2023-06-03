package com.example.ajarin.android.message.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.android.core.domain.preferences.Preferences
import com.example.ajarin.mentorProfile.domain.use_cases.GetMentorById
import com.example.ajarin.message.domain.use_cases.CreateInbox
import com.example.ajarin.message.domain.use_cases.GetMessagesById
import com.example.ajarin.message.domain.use_cases.InsertMessage
import com.example.ajarin.message.presentation.MessageEvent
import com.example.ajarin.message.presentation.MessageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidMessageViewModel @Inject constructor(
    private val getMessagesById: GetMessagesById,
    private val getMentorById: GetMentorById,
    private val createInbox: CreateInbox,
    private val insertMessage: InsertMessage,
    private val preferences: Preferences,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val viewModel by lazy {
        MessageViewModel(
            getMessageById = getMessagesById,
            getMentorById = getMentorById,
            createInbox = createInbox,
            insertMessage = insertMessage,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: MessageEvent) {
        viewModel.onEvent(event)
    }

    fun sendMessage() {
        viewModel.onEvent(
            MessageEvent.SendMessage(
                userId = "U1"
            )
        )
    }

    init {
        val mentorId = savedStateHandle.get<String>("mentor_id")

        mentorId?.let {
            viewModel.init(
                mentorId = it,
                userId = "U1"
            )
        }
    }
}