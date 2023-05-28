package com.example.ajarin.android.search_mentor.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.core.utils.UiEvent
import com.example.ajarin.searchMentor.domain.use_cases.SearchMentor
import com.example.ajarin.searchMentor.presentation.SearchMentorEvent
import com.example.ajarin.searchMentor.presentation.SearchMentorViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class AndroidSearchMentorViewModel @Inject constructor(
    private val searchMentor: SearchMentor
): ViewModel() {
    private val viewModel by lazy {
        SearchMentorViewModel(
            searchMentor = searchMentor,
            coroutineScope = viewModelScope
        )
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val state = viewModel.state

    fun onEvent(event: SearchMentorEvent) {
        viewModel.onEvent(event)
    }
}