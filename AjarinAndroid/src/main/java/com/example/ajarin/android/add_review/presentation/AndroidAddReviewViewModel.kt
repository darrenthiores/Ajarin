package com.example.ajarin.android.add_review.presentation

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.addReview.presentation.AddReviewEvent
import com.example.ajarin.addReview.presentation.AddReviewViewModel
import com.example.ajarin.session.domain.use_cases.GetSessionById
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidAddReviewViewModel @Inject constructor(
    private val getSessionById: GetSessionById,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val viewModel by lazy {
        AddReviewViewModel(
            getSessionById = getSessionById,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel
        .uiEvent
    val images = mutableStateListOf<Uri>()

    fun onEvent(event: AddReviewEvent) {
        viewModel.onEvent(event)
    }

    fun addImage(uri: Uri) {
        images.add(uri)
    }

    fun removeImage(uri: Uri) {
        images.remove(uri)
    }

    init {
        val sessionId = savedStateHandle.get<String>("session_id")

        viewModel.init(
            sessionId = sessionId ?: ""
        )
    }
}