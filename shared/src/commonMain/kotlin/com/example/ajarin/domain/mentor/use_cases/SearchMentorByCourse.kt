package com.example.ajarin.domain.mentor.use_cases

import com.example.ajarin.presentation.home.Course
import com.example.ajarin.presentation.home.Mentor
import com.example.ajarin.presentation.home.dummyMentors

class SearchMentorByCourse {
    fun execute(
        course: Course
    ): List<Mentor> {
        return dummyMentors.filter { mentor ->
            mentor.courses.contains(course)
        }
    }
}