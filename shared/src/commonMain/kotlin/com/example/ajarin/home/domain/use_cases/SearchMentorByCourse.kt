package com.example.ajarin.home.domain.use_cases

import com.example.ajarin.home.presentation.Course
import com.example.ajarin.home.presentation.Mentor
import com.example.ajarin.home.presentation.dummyMentors

class SearchMentorByCourse {
    fun execute(
        course: Course
    ): List<Mentor> {
        return dummyMentors.filter { mentor ->
            mentor.courses.contains(course)
        }
    }
}