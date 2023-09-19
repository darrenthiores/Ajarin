package com.example.ajarin.presentation.sessionAsMentor

import com.example.ajarin.domain.order.model.Order
import com.example.ajarin.domain.user.model.User
import com.example.ajarin.presentation.session.SessionInfo

data class SessionAsMentorState(
    val historySession: Order? = null,
    val isSessionLoading: Boolean = false,
    val isSessionError: Error? = null,
    val user: User? = null,
    val isUserLoading: Boolean = false,
    val isUserError: Error? = null,
    val sessionInfo: SessionInfo? = null,
    val sessionInfoLoading: Boolean = false,
    val sessionInfoError: Error? = null,
    val isSessionInfoEditable: Boolean = false,
    val mainLinkText: String = "",
    val backupLinkText: String = "",
    val materialLinkText: String = ""
)