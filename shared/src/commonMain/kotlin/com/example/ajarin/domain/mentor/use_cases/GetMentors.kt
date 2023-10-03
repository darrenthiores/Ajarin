package com.example.ajarin.domain.mentor.use_cases

import com.example.ajarin.domain.mentor.model.Mentor
import com.example.ajarin.domain.mentor.repository.MentorRepository
import com.example.ajarin.domain.utils.Resource

class GetMentors(
    private val repository: MentorRepository
) {
    suspend operator fun invoke(
        page: Int
    ): List<Mentor> {
        val result = repository.getMentors(
            page = page
        )

        return when (result) {
            is Resource.Error -> emptyList()
            is Resource.Loading -> emptyList()
            is Resource.Success -> result.data ?: emptyList()
        }
    }
}