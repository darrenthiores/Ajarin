package com.example.ajarin.session.presentation

import com.example.ajarin.history.presentation.HistorySession
import com.example.ajarin.home.presentation.Mentor

data class SessionState(
    val historySession: HistorySession? = null,
    val isSessionLoading: Boolean = false,
    val isSessionError: Error? = null,
    val mentor: Mentor? = null,
    val isMentorLoading: Boolean = false,
    val isMentorError: Error? = null,
    val sessionInfo: SessionInfo? = null,
    val sessionInfoLoading: Boolean = false,
    val sessionInfoError: Error? = null
)

data class SessionInfo(
    val mainLink: String,
    val backupLink: String,
    val materialLink: String
)

val dummySessionInfo = SessionInfo(
    mainLink = "https://zoom.us/",
    backupLink = "https://meet.google.com/",
    materialLink = "https://www.canva.com/"
)