package com.example.ajarin.presentation.session

import com.example.ajarin.domain.mentor.model.Mentor
import com.example.ajarin.domain.order.model.Order

data class SessionState(
    val historySession: Order? = null,
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