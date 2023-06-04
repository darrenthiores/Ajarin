package com.example.ajarin.android.apply_as_mentor.presentation

import android.net.Uri

sealed class AndroidApplyAsMentorEvent {
    data class OnSelectIdFile(val file: Uri): AndroidApplyAsMentorEvent()
    data class OnSelectInstitutionIdFile(val file: Uri): AndroidApplyAsMentorEvent()
    data class OnSelectGradeReportFile(val file: Uri): AndroidApplyAsMentorEvent()
}
