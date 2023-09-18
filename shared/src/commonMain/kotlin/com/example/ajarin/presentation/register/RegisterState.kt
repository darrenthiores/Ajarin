package com.example.ajarin.presentation.register

import com.example.ajarin.domain.validation.utils.ValidationError

data class RegisterState(
    val currentSection: RegisterSection = RegisterSection.CreateAccount,
    val name: String = "",
    val nameError: ValidationError? = null,
    val email: String = "",
    val emailError: ValidationError? = null,
    val password: String = "",
    val passwordError: ValidationError? = null,
    val isPasswordVisible: Boolean = false,
    val registerEnabled: Boolean = false,
    val number: String = "",
    val numberError: ValidationError? = null,
    val sendOtpEnabled: Boolean = false,
    val otp: String = "",
    val otpError: ValidationError? = null,
    val verifyOtpEnabled: Boolean = false,
    val otpSendCount: Int = 0,
    val otpCountDown: Int = 0,
    val verificationId: String? = null,
    val sendOtpError: String? = null,
    val sendOtpLoading: Boolean = false,
    val verifyOtpError: String? = null,
    val verifyOtpLoading: Boolean = false,
    val registerLoading: Boolean = false,
    val registerError: String? = null,
    val registerSuccess: Boolean = false
)

enum class RegisterSection {
    CreateAccount,
    FillNumber,
    NumberVerification
}