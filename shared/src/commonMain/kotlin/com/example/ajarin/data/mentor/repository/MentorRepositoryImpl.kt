package com.example.ajarin.data.mentor.repository

import com.example.ajarin.data.core.remote.dto.request.IdRequest
import com.example.ajarin.data.core.utils.ApiResponse
import com.example.ajarin.data.mentor.mapper.toMentor
import com.example.ajarin.data.mentor.remote.MentorRemoteDataSource
import com.example.ajarin.data.mentor.remote.dto.request.ApplyAsMentorRequest
import com.example.ajarin.data.mentor.remote.dto.request.SearchMentorRequest
import com.example.ajarin.domain.mentor.model.Mentor
import com.example.ajarin.domain.mentor.repository.MentorRepository
import com.example.ajarin.domain.utils.Resource

class MentorRepositoryImpl(
    private val remoteDataSource: MentorRemoteDataSource
): MentorRepository {
    override suspend fun getMentor(): Resource<Mentor> {
        val result = remoteDataSource.getMentor()

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data.toMentor())
        }
    }

    override suspend fun getMentorById(id: String): Resource<Mentor> {
        val result = remoteDataSource.getMentorById(
            request = IdRequest(
                id = id
            )
        )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data.toMentor())
        }
    }

    override suspend fun getMentors(page: Int): Resource<List<Mentor>> {
        val result = remoteDataSource.getMentors(
            page = page
        )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(
                result.data.map { it.toMentor() }
            )
        }
    }

    override suspend fun searchMentor(
        name: String,
        education: String,
        rating: Double,
        courseId: String,
        price: String,
        page: Int
    ): Resource<List<Mentor>> {
        val result = remoteDataSource.searchMentor(
            request = SearchMentorRequest(
                name = name,
                education = education,
                rating = rating,
                courseId = courseId,
                price = price
            ),
            page = page
        )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(
                result.data.map { it.toMentor() }
            )
        }
    }

    override suspend fun applyAsMentor(
        id: String,
        education: String,
        courses: List<String>,
        price: String
    ): Resource<Unit> {
        val result = remoteDataSource.applyAsMentor(
            request = ApplyAsMentorRequest(
                id = id,
                education = education,
                courses = courses,
                price = price
            )
        )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(result.data)
        }
    }
}