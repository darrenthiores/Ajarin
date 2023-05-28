package com.example.ajarin.profile.presentation

data class ProfileState(
    val user: User? = null,
    val isFetchingUser: Boolean = false,
    val isDialogShow: Boolean = false
)

data class User(
    val id: String,
    val name: String,
    val email: String,
    val number: String,
    val roleType: String
)