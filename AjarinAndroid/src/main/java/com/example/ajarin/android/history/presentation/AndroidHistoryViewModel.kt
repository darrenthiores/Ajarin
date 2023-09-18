package com.example.ajarin.android.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.domain.order.use_cases.GetHistory
import com.example.ajarin.presentation.history.HistoryViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidHistoryViewModel @Inject constructor(
    private val getHistory: GetHistory
): ViewModel() {
    private val viewModel by lazy {
        HistoryViewModel(
            getHistory = getHistory,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
}