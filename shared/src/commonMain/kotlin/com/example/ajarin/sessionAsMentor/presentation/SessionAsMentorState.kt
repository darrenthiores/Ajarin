package com.example.ajarin.sessionAsMentor.presentation

import com.example.ajarin.history.presentation.HistorySession
import com.example.ajarin.profile.presentation.User
import com.example.ajarin.session.presentation.SessionInfo

data class SessionAsMentorState(
    val historySession: HistorySession? = null,
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