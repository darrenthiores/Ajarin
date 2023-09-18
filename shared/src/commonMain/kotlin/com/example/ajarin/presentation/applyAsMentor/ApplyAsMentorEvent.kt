package com.example.ajarin.presentation.applyAsMentor

import com.example.ajarin.presentation.home.Course
import com.example.ajarin.presentation.mentorProfile.Session

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
