package com.example.ajarin.applyAsMentor.presentation

import com.example.ajarin.core.utils.errors.ValidationError
import com.example.ajarin.home.presentation.Course
import com.example.ajarin.mentorProfile.presentation.Session

data class ApplyAsMentorState(
    val fullName: String = "",
    val fullNameError: ValidationError? = null,
    val id: String = "",
    val idError: ValidationError? = null,
    val institutionName: String = "",
    val institutionError: ValidationError? = null,
    val lastEducation: String? = null,
    val isEducationDropDownOpen: Boolean = false,
    val selectedCourses: List<Course> = emptyList(),
    val coursesError: ValidationError? = null,
    val selectedSchedule: List<Session> = emptyList(),
    val proposedFee: String = "",
    val proposedFeeError: ValidationError? = null,
    val isApplying: Boolean = false,
    val applyError: Error? = null,
    val applySuccess: Boolean = false
)
