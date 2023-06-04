package com.example.ajarin.applyAsMentor.presentation

import com.example.ajarin.home.presentation.Course
import com.example.ajarin.mentorProfile.presentation.Session

sealed class ApplyAsMentorEvent {
    data class OnFullNameChange(val newText: String): ApplyAsMentorEvent()
    data class OnIdChange(val newText: String): ApplyAsMentorEvent()
    data class OnInstitutionChange(val newText: String): ApplyAsMentorEvent()
    data class OnPickEducation(val education: String): ApplyAsMentorEvent()
    data class ToggleEducationDropDown(val isOpen: Boolean): ApplyAsMentorEvent()
    data class OnPickCourse(val course: Course): ApplyAsMentorEvent()
    data class OnPickSchedule(val schedule: Session): ApplyAsMentorEvent()
    data class OnFeeChange(val newText: String): ApplyAsMentorEvent()
    object OnApply: ApplyAsMentorEvent()
}
