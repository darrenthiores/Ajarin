package com.example.ajarin.presentation.profile

import com.example.ajarin.domain.core.utils.toCommonStateFlow
import com.example.ajarin.domain.user.use_cases.GetUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUser: GetUser,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.toCommonStateFlow()

    init {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isFetchingUser = true
            )

            _state.value = state.value.copy(
                isFetchingUser = false,
                user = dummyUsers.firstOrNull()
            )

            return@launch

            val result = getUser()
            _state.value = state.value.copy(
                isFetchingUser = false,
                user = result
            )
        }
    }

    fun onEvent(event: ProfileEvent) {
        when(event) {
            ProfileEvent.LogOut -> {
                _state.value = state.value.copy(
                    isDialogShow = false
                )
            }
            is ProfileEvent.ToggleDialog -> {
                _state.value = state.value.copy(
                    isDialogShow = event.isShow
                )
            }
        }
    }
}