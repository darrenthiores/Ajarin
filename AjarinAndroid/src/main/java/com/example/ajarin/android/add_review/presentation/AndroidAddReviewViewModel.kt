package com.example.ajarin.android.add_review.presentation

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.domain.order.use_cases.GetOrderById
import com.example.ajarin.domain.review.use_cases.CreateReview
import com.example.ajarin.presentation.addReview.AddReviewEvent
import com.example.ajarin.presentation.addReview.AddReviewViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidAddReviewViewModel @Inject constructor(
    private val getOrderById: GetOrderById,
    private val createReview: CreateReview,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val viewModel by lazy {
        AddReviewViewModel(
            getOrderById = getOrderById,
            addReview = createReview,
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