package com.example.ajarin.searchMentor.domain.use_cases

import com.example.ajarin.home.presentation.Course
import com.example.ajarin.home.presentation.Mentor
import com.example.ajarin.home.presentation.dummyMentors

class SearchMentor {
    fun execute(
        name: String,
        course: Course?,
        rating: Int,
        price: String,
        education: String
    ): List<Mentor> {
        return dummyMentors
            .asSequence()
            .filter {
                it.name.contains(name)
                        && (course?.let { course ->  it.courses.contains(course) } ?: true)
                        && (if (rating!=0) (it.rating.toDoubleOrNull() ?: 0.0) > rating else true)
                        && (if (price!="") it.priceCategory == price else true)
                        && (if (education!="") it.education == education else true)
            }
            .toList()
    }
}