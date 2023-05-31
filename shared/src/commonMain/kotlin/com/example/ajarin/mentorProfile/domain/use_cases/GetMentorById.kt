package com.example.ajarin.mentorProfile.domain.use_cases

import com.example.ajarin.core.utils.Resource
import com.example.ajarin.home.presentation.Mentor
import com.example.ajarin.home.presentation.dummyMentors

class GetMentorById {
    fun execute(
        id: String
    ): Resource<Mentor> {
        dummyMentors
            .firstOrNull { it.id == id }
            ?.let { mentor ->
                return Resource.Success(
                    data = mentor
                )
            } ?: return Resource.Error(
                message = "There is no mentor with such id"
            )
    }
}