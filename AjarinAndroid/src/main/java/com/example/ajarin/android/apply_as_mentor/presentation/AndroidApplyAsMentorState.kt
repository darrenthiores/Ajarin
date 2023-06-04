package com.example.ajarin.android.apply_as_mentor.presentation

import android.net.Uri

data class AndroidApplyAsMentorState(
    val idFile: Uri? = null,
    val idFileError: Error? = null,
    val institutionIdFile: Uri? = null,
    val institutionIdFileError: Error? = null,
    val gradeReportFile: Uri? = null,
    val gradeReportFileError: Error? = null
)
