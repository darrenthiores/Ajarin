package com.example.ajarin.domain.mentor.repository

import com.example.ajarin.domain.mentor.model.Mentor
import com.example.ajarin.domain.utils.Resource

interface MentorRepository {
    suspend fun getMentor(): Resource<Mentor>

    suspend fun getMentorById(
        id: String
    ): Resource<Mentor>

    suspend fun getMentors(
        page: Int
    ): Resource<List<Mentor>>

    suspend fun searchMentor(
        name: String,
        education: String,
        rating: Double,
        courseId: String,
        price: String,
        page: Int
    ): Resource<List<Mentor>>

    suspend fun applyAsMentor(
        id: String,
        education: String,
        courses: List<String>,
        price: String
    ): Resource<Unit>
}