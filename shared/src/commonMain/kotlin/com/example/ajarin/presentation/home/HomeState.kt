package com.example.ajarin.presentation.home

import com.example.ajarin.domain.core.model.Course
import com.example.ajarin.domain.core.model.allCourses

data class HomeState(
    val courses: List<Course> = allCourses,
    val selectedCourse: Course = Course(
        id = "0", name = "All"
    ),
    val unreadMessageCount: Int = 0
)