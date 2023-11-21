package com.example.ajarin.presentation.sessionAsMentor

import com.example.ajarin.domain.core.utils.toCommonStateFlow
import com.example.ajarin.domain.order.model.dummyHistory
import com.example.ajarin.domain.order.use_cases.GetOrderById
import com.example.ajarin.domain.order.use_cases.UpdateOrder
import com.example.ajarin.domain.user.use_cases.GetUserById
import com.example.ajarin.domain.utils.Resource
import com.example.ajarin.presentation.profile.dummyUsers
import com.example.ajarin.presentation.session.dummySessionInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SessionAsMentorViewModel(
    private val getOrderById: GetOrderById,
    private val getUserById: GetUserById,
    private val updateOrder: UpdateOrder,
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
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        historySession = state.value.historySession?.copy(
                            status = "3"
                        )
                    )

                    return@launch

                    val result = updateOrder(
                        id = state.value.historySession?.id ?: return@launch,
                        mainLink = null,
                        backupLink = null,
                        materialLink = null,
                        status = "3"
                    )

                    when (result) {
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                isSessionError = Error(result.message)
                            )
                        }
                        is Resource.Loading -> Unit
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                historySession = state.value.historySession?.copy(
                                    status = "3"
                                )
                            )
                        }
                    }
                }
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
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        sessionInfo = state.value.sessionInfo?.copy(
                            mainLink = state.value.mainLinkText,
                            backupLink = state.value.backupLinkText,
                            materialLink = state.value.materialLinkText
                        ),
                        isSessionInfoEditable = false
                    )

                    return@launch

                    val result = updateOrder(
                        id = state.value.historySession?.id ?: return@launch,
                        mainLink = state.value.mainLinkText,
                        backupLink = state.value.backupLinkText,
                        materialLink = state.value.materialLinkText,
                        status = state.value.historySession?.status ?: return@launch
                    )

                    when (result) {
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                isSessionError = Error(result.message)
                            )
                        }
                        is Resource.Loading -> Unit
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                sessionInfo = state.value.sessionInfo?.copy(
                                    mainLink = state.value.mainLinkText,
                                    backupLink = state.value.backupLinkText,
                                    materialLink = state.value.materialLinkText
                                ),
                                isSessionInfoEditable = false
                            )
                        }
                    }
                }
            }
            SessionAsMentorEvent.OnStartClass -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        historySession = state.value.historySession?.copy(
                            status = "2"
                        )
                    )

                    return@launch

                    val result = updateOrder(
                        id = state.value.historySession?.id ?: return@launch,
                        mainLink = null,
                        backupLink = null,
                        materialLink = null,
                        status = "2"
                    )

                    when (result) {
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                isSessionError = Error(result.message)
                            )
                        }
                        is Resource.Loading -> Unit
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                historySession = state.value.historySession?.copy(
                                    status = "2"
                                )
                            )
                        }
                    }
                }
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

            _state.value = state.value.copy(
                historySession = dummyHistory.firstOrNull { it.id == sessionId },
                isSessionLoading = false,
                isSessionError = null
            )

            return@launch

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

            _state.value = state.value.copy(
                user = dummyUsers.firstOrNull { it.id == userId },
                isUserLoading = false,
                isUserError = null
            )

            return@launch

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