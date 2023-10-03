package com.example.ajarin.android.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.android.core.domain.preferences.Preferences
import com.example.ajarin.domain.user.use_cases.GetUser
import com.example.ajarin.domain.utils.UiEvent
import com.example.ajarin.presentation.profile.ProfileEvent
import com.example.ajarin.presentation.profile.ProfileViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AndroidProfileViewModel @Inject constructor(
    private val getUser: GetUser,
    private val preferences: Preferences,
): ViewModel() {
    private val viewModel by lazy {
        ProfileViewModel(
            getUser = getUser,
            coroutineScope = viewModelScope
        )
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val state = viewModel.state

    fun onEvent(event: ProfileEvent) {
        if (event == ProfileEvent.LogOut) {
            preferences.saveShouldShowOnBoarding(true)

            viewModelScope.launch {
                _uiEvent.send(UiEvent.Success)
            }
        }

        viewModel.onEvent(event)
    }
}