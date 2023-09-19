package com.example.ajarin.data.mentor.remote.service

import com.example.ajarin.data.core.remote.dto.request.IdRequest
import com.example.ajarin.data.core.remote.dto.response.BaseResponse
import com.example.ajarin.data.mentor.remote.dto.request.ApplyAsMentorRequest
import com.example.ajarin.data.mentor.remote.dto.request.SearchMentorRequest
import com.example.ajarin.data.mentor.remote.dto.response.MentorResponse

interface MentorService {
    suspend fun getMentor(): BaseResponse<MentorResponse>
    suspend fun getMentorById(request: IdRequest): BaseResponse<MentorResponse>
    suspend fun getMentors(page: Int): BaseResponse<List<MentorResponse>>
    suspend fun searchMentor(
        request: SearchMentorRequest,
        page: Int
    ): BaseResponse<List<MentorResponse>>
    suspend fun applyAsMentor(request: ApplyAsMentorRequest): BaseResponse<Any?>

    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080"
        const val MENTOR_URL = "$BASE_URL/mentor"
        const val APPLY_AS_MENTOR_URL = "$BASE_URL/mentor/register"
        const val SEARCH_MENTOR_URL = "$BASE_URL/mentor/search"
    }
}