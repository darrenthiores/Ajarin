package com.example.ajarin.presentation.sessionAsMentor

sealed class SessionAsMentorEvent {
    object OnStartClass: SessionAsMentorEvent()
    object OnFinishClass: SessionAsMentorEvent()
    object OnToggleEditSessionInfo: SessionAsMentorEvent()
    data class OnMainLinkTextChange(val newLink: String): SessionAsMentorEvent()
    data class OnBackupLinkTextChange(val newLink: String): SessionAsMentorEvent()
    data class OnMaterialLinkTextChange(val newLink: String): SessionAsMentorEvent()
    object OnSaveEdit: SessionAsMentorEvent()
}
