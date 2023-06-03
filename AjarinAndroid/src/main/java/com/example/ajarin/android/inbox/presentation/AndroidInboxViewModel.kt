package com.example.ajarin.android.inbox.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.android.core.domain.preferences.Preferences
import com.example.ajarin.inbox.domain.use_cases.GetInbox
import com.example.ajarin.inbox.presentation.InboxViewModel
import com.example.ajarin.mentorProfile.domain.use_cases.GetMentorById
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidInboxViewModel @Inject constructor(
    private val getInbox: GetInbox,
    private val getMentorById: GetMentorById,
    private val preferences: Preferences
): ViewModel() {
    private val viewModel by lazy {
        InboxViewModel(
            getInbox = getInbox,
            getMentorById = getMentorById,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    init {
        viewModel.initInbox(
            userId = "U1"
        )
    }
}