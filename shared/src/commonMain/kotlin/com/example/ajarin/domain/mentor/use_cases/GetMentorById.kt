package com.example.ajarin.domain.mentor.use_cases

import com.example.ajarin.domain.mentor.model.Mentor
import com.example.ajarin.domain.mentor.repository.MentorRepository
import com.example.ajarin.domain.utils.Resource

class GetMentorById(
    private val repository: MentorRepository
) {
    suspend operator fun invoke(
        id: String
    ): Resource<Mentor> {
        return repository.getMentorById(
            id = id
        )
    }
}