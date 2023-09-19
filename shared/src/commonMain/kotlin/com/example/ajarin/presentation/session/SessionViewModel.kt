package com.example.ajarin.presentation.session

import com.example.ajarin.domain.core.utils.toCommonStateFlow
import com.example.ajarin.domain.mentor.use_cases.GetMentorById
import com.example.ajarin.domain.order.use_cases.GetOrderById
import com.example.ajarin.domain.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SessionViewModel(
    private val getOrderById: GetOrderById,
    private val getMentorById: GetMentorById,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(SessionState())
    val state = _state.toCommonStateFlow()

    fun init(
        sessionId: String,
        mentorId: String
    ) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isSessionLoading = true
            )

            val result = getOrderById(
                id = sessionId
            )

            when(result) {
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

        viewModelScope.launch {
            _state.value = state.value.copy(
                isMentorLoading = true
            )

            val result = getMentorById(
                id = mentorId
            )

            when(result) {
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isMentorLoading = false,
                        isMentorError = Error(result.message)
                    )
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        mentor = result.data,
                        isSessionLoading = false,
                        isSessionError = null
                    )
                }
            }
        }

        viewModelScope.launch {
            _state.value = state.value.copy(
                sessionInfo = dummySessionInfo
            )
        }
    }
}