package com.example.ajarin.presentation.applyAsMentor

import com.example.ajarin.domain.validation.utils.ValidationError
import com.example.ajarin.presentation.home.Course
import com.example.ajarin.presentation.mentorProfile.Session

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
