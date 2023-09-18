package com.example.ajarin.android.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.android.core.domain.preferences.Preferences
import com.example.ajarin.domain.mentor.use_cases.SearchMentorByCourse
import com.example.ajarin.domain.message.use_cases.GetUnreadCount
import com.example.ajarin.domain.utils.UiEvent
import com.example.ajarin.presentation.home.HomeEvent
import com.example.ajarin.presentation.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class AndroidHomeViewModel @Inject constructor(
    private val searchMentorByCourse: SearchMentorByCourse,
    private val getUnreadCount: GetUnreadCount,
    private val preferences: Preferences
): ViewModel() {
    private val viewModel by lazy {
        HomeViewModel(
            searchMentorByCourse = searchMentorByCourse,
            getUnreadCount = getUnreadCount,
            coroutineScope = viewModelScope
        )
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val state = viewModel.state

    fun onEvent(event: HomeEvent) {
        viewModel.onEvent(event)
    }

    init {
        viewModel.initUnreadCount(
            userId = "U1"
        )
    }
}