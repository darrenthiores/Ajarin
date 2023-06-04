package com.example.ajarin.profile.presentation

import com.example.ajarin.core.domain.utils.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow

class ProfileViewModel(
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.toCommonStateFlow()

    init {
        _state.value = state.value.copy(
            user = dummyUsers[0]
        )
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