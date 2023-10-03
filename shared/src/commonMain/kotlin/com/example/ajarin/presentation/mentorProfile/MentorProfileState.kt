package com.example.ajarin.presentation.mentorProfile

import com.example.ajarin.domain.mentor.model.Mentor

data class MentorProfileState(
    val mentor: Mentor? = null,
    val isFetching: Boolean = false,
    val isError: Error? = null
)