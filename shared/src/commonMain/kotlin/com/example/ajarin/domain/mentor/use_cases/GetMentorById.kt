package com.example.ajarin.domain.mentor.use_cases

import com.example.ajarin.domain.utils.Resource
import com.example.ajarin.presentation.home.Mentor
import com.example.ajarin.presentation.home.dummyMentors

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