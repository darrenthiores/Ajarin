package com.example.ajarin.presentation.profile

sealed class ProfileEvent {
    object LogOut: ProfileEvent()
    data class ToggleDialog(val isShow: Boolean): ProfileEvent()
}