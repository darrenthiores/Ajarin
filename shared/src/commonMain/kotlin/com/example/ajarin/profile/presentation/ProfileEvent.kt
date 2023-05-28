package com.example.ajarin.profile.presentation

sealed class ProfileEvent {
    object LogOut: ProfileEvent()
    data class ToggleDialog(val isShow: Boolean): ProfileEvent()
}