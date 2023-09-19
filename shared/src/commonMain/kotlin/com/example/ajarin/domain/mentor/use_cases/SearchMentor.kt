package com.example.ajarin.domain.mentor.use_cases

import com.example.ajarin.domain.core.model.Course
import com.example.ajarin.domain.mentor.model.Mentor
import com.example.ajarin.domain.mentor.repository.MentorRepository
import com.example.ajarin.domain.utils.Resource

class SearchMentor(
    private val repository: MentorRepository
) {
    suspend operator fun invoke(
        name: String,
        course: Course?,
        rating: Int,
        price: String,
        education: String,
        page: Int
    ): Resource<List<Mentor>> {
        return repository.searchMentor(
            name = name,
            education = education,
            rating = rating.toDouble(),
            courseId = course?.id ?: "",
            price = price,
            page = page
        )
    }
}