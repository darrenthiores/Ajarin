package com.example.ajarin.presentation.addReview

import com.example.ajarin.domain.core.utils.toCommonFlow
import com.example.ajarin.domain.core.utils.toCommonStateFlow
import com.example.ajarin.domain.order.use_cases.GetOrderById
import com.example.ajarin.domain.review.use_cases.CreateReview
import com.example.ajarin.domain.utils.Resource
import com.example.ajarin.domain.utils.UiEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddReviewViewModel(
    private val getOrderById: GetOrderById,
    private val addReview: CreateReview,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(AddReviewState())
    val state = _state.toCommonStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent
        .receiveAsFlow()
        .toCommonFlow()

    fun onEvent(event: AddReviewEvent) {
        when(event) {
            is AddReviewEvent.OnPickRating -> {
                _state.value = state.value.copy(
                    rating = event.rating
                )
            }
            AddReviewEvent.OnPostReview -> {
                viewModelScope.launch {
                    val result = addReview(
                        orderId = state.value.historySession?.id ?: return@launch,
                        comment = state.value.reviewText,
                        rating = state.value.rating.toDouble()
                    )

                    when (result) {
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                isPostError = Error(result.message),
                                isPosting = false
                            )
                        }
                        is Resource.Loading -> Unit
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                isPostSuccess = true,
                                isPosting = false
                            )

                            viewModelScope.launch {
                                _uiEvent.send(
                                    UiEvent.Success
                                )
                            }
                        }
                    }
                }
            }
            is AddReviewEvent.OnReviewChange -> {
                _state.value = state.value.copy(
                    reviewText = event.newText
                )
            }
        }
    }

    fun init(
        sessionId: String
    ) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isSessionLoading = true
            )

            val result = getOrderById(
                id = sessionId
            )

            when (result) {
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isSessionLoading = false,
                        isSessionError = Error(result.message)
                    )
                }

                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        historySession = result.data,
                        isSessionLoading = false,
                        isSessionError = null
                    )
                }
            }
        }
    }
}