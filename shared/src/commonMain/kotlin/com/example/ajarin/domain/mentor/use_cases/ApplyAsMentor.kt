package com.example.ajarin.domain.mentor.use_cases

import com.example.ajarin.domain.mentor.repository.MentorRepository
import com.example.ajarin.domain.utils.Resource

class ApplyAsMentor(
    private val repository: MentorRepository
) {
    suspend operator fun invoke(
        id: String,
        education: String,
        courses: List<String>,
        price: String,
    ): Resource<Unit> {
        return repository.applyAsMentor(
            id = id,
            education = education,
            courses = courses,
            price = price
        )
    }
}