package com.example.ajarin.sessionAsMentor.presentation

sealed class SessionAsMentorEvent {
    object OnStartClass: SessionAsMentorEvent()
    object OnFinishClass: SessionAsMentorEvent()
    object OnToggleEditSessionInfo: SessionAsMentorEvent()
    data class OnMainLinkTextChange(val newLink: String): SessionAsMentorEvent()
    data class OnBackupLinkTextChange(val newLink: String): SessionAsMentorEvent()
    data class OnMaterialLinkTextChange(val newLink: String): SessionAsMentorEvent()
    object OnSaveEdit: SessionAsMentorEvent()
}
