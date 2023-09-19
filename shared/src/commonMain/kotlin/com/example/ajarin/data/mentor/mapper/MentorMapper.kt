package com.example.ajarin.data.mentor.mapper

import com.example.ajarin.data.mentor.remote.dto.response.MentorResponse
import com.example.ajarin.domain.core.model.Course
import com.example.ajarin.domain.mentor.model.Mentor

fun MentorResponse.toMentor(): Mentor {
    return Mentor(
        id = id,
        photoUrl = photoUrl,
        name = name,
        education = education,
        rating = rating.toString(),
        courses = courses.map { course ->
            Course(
                id = course.id,
                name = course.name
            )
        },
        price = price,
        priceCategory = priceCategory
    )
}