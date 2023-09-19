package com.example.ajarin.presentation.sessionAsMentor

import com.example.ajarin.domain.core.utils.toCommonStateFlow
import com.example.ajarin.domain.order.use_cases.GetOrderById
import com.example.ajarin.domain.user.use_cases.GetUserById
import com.example.ajarin.domain.utils.Resource
import com.example.ajarin.presentation.session.dummySessionInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SessionAsMentorViewModel(
    private val getOrderById: GetOrderById,
    private val getUserById: GetUserById,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(SessionAsMentorState())
    val state = _state.toCommonStateFlow()

    fun onEvent(event: SessionAsMentorEvent) {
        when (event) {
            is SessionAsMentorEvent.OnBackupLinkTextChange ->{
                _state.value = state.value.copy(
                    backupLinkText = event.newLink
                )
            }
            SessionAsMentorEvent.OnFinishClass -> {
                _state.value = state.value.copy(
                    historySession = state.value.historySession?.copy(
                        status = "3"
                    )
                )
            }
            is SessionAsMentorEvent.OnMainLinkTextChange -> {
                _state.value = state.value.copy(
                    mainLinkText = event.newLink
                )
            }
            is SessionAsMentorEvent.OnMaterialLinkTextChange -> {
                _state.value = state.value.copy(
                    materialLinkText = event.newLink
                )
            }
            SessionAsMentorEvent.OnSaveEdit -> {
                _state.value = state.value.copy(
                    sessionInfo = state.value.sessionInfo?.copy(
                        mainLink = state.value.mainLinkText,
                        backupLink = state.value.backupLinkText,
                        materialLink = state.value.materialLinkText
                    ),
                    isSessionInfoEditable = false
                )
            }
            SessionAsMentorEvent.OnStartClass -> {
                _state.value = state.value.copy(
                    historySession = state.value.historySession?.copy(
                        status = "2"
                    )
                )
            }
            SessionAsMentorEvent.OnToggleEditSessionInfo -> {
                _state.value = state.value.copy(
                    isSessionInfoEditable = !state.value.isSessionInfoEditable,
                    mainLinkText = state.value.sessionInfo?.mainLink ?: "",
                    backupLinkText = state.value.sessionInfo?.backupLink ?: "",
                    materialLinkText = state.value.sessionInfo?.materialLink ?: ""
                )
            }
        }
    }

    fun init(
        sessionId: String,
        userId: String
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
                isUserLoading = true
            )

            val result = getUserById(
                id = userId
            )

            when(result) {
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isUserLoading = false,
                        isUserError = Error(result.message)
                    )
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        user = result.data,
                        isUserLoading = false,
                        isUserError = null
                    )
                }
            }
        }

        viewModelScope.launch {
            _state.value = state.value.copy(
                sessionInfo = dummySessionInfo,
                mainLinkText = dummySessionInfo.mainLink,
                backupLinkText = dummySessionInfo.backupLink,
                materialLinkText = dummySessionInfo.materialLink
            )
        }
    }
}