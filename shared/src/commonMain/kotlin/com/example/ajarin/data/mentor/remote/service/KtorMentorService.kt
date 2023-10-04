package com.example.ajarin.data.mentor.remote.service

import com.example.ajarin.data.core.remote.dto.request.IdRequest
import com.example.ajarin.data.core.remote.dto.response.ApplyResponse
import com.example.ajarin.data.core.remote.dto.response.BaseResponse
import com.example.ajarin.data.mentor.remote.dto.request.ApplyAsMentorRequest
import com.example.ajarin.data.mentor.remote.dto.request.SearchMentorRequest
import com.example.ajarin.data.mentor.remote.dto.response.MentorResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class KtorMentorService(
    private val client: HttpClient
): MentorService {
    override suspend fun getMentor(): BaseResponse<MentorResponse> {
        val result = client.get {
            url(MentorService.MENTOR_URL)
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    override suspend fun getMentorById(request: IdRequest): BaseResponse<MentorResponse> {
        val result = client.post {
            url(MentorService.MENTOR_URL)
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

    override suspend fun getMentors(
        page: Int
    ): BaseResponse<List<MentorResponse>> {
        val result = client.get {
            url(MentorService.MENTOR_URL + "/$page")
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    override suspend fun searchMentor(
        request: SearchMentorRequest,
        page: Int
    ): BaseResponse<List<MentorResponse>> {
        val result = client.post {
            url(MentorService.SEARCH_MENTOR_URL + "/$page")
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

    override suspend fun applyAsMentor(request: ApplyAsMentorRequest): ApplyResponse {
        val result = client.post {
            url(MentorService.APPLY_AS_MENTOR_URL)
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

}