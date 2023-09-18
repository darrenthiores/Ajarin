package com.example.ajarin.presentation.register

import com.example.ajarin.domain.utils.Resource

sealed class RegisterEvent {
    data class OnNameChange(val name: String): RegisterEvent()
    data class OnEmailChange(val email: String): RegisterEvent()
    data class OnPasswordChange(val password: String): RegisterEvent()
    object ToggleShowPassword: RegisterEvent()
    data class OnNumberChange(val number: String): RegisterEvent()
    data class OnSendOtpResult(val result: Resource<String>): RegisterEvent()
    data class OnOtpChange(val otp: String): RegisterEvent()
    object OnVerifyOtp: RegisterEvent()
    data class UpdateSection(val section: RegisterSection): RegisterEvent()
    object Register: RegisterEvent()
}