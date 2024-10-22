package com.example.ajarin.data.mentor.remote

import com.example.ajarin.data.core.remote.dto.request.IdRequest
import com.example.ajarin.data.core.utils.ApiResponse
import com.example.ajarin.data.core.utils.tryCatch
import com.example.ajarin.data.mentor.remote.dto.request.ApplyAsMentorRequest
import com.example.ajarin.data.mentor.remote.dto.request.SearchMentorRequest
import com.example.ajarin.data.mentor.remote.dto.response.MentorResponse
import com.example.ajarin.data.mentor.remote.service.MentorService
import com.example.ajarin.domain.core.dispatchers.DispatchersProvider
import kotlinx.coroutines.withContext

class MentorRemoteDataSource(
    private val apiService: MentorService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun getMentor(): ApiResponse<MentorResponse> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getMentor()

                if (result.meta.success) {
                    result.data?.let {
                        return@tryCatch ApiResponse.Success(it)
                    }

                    ApiResponse.Error(result.meta.message)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun getMentorById(
        request: IdRequest
    ): ApiResponse<MentorResponse> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getMentorById(
                    request = request
                )

                if (result.meta.success) {
                    result.data?.let {
                        return@tryCatch ApiResponse.Success(it)
                    }

                    ApiResponse.Error(result.meta.message)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun getMentors(
        page: Int
    ): ApiResponse<List<MentorResponse>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getMentors(
                    page = page
                )

                if (result.meta.success) {
                    result.data?.let {
                        return@tryCatch ApiResponse.Success(it)
                    }

                    ApiResponse.Error(result.meta.message)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun searchMentor(
        request: SearchMentorRequest,
        page: Int
    ): ApiResponse<List<MentorResponse>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.searchMentor(
                    request = request,
                    page = page
                )

                if (result.meta.success) {
                    result.data?.let {
                        return@tryCatch ApiResponse.Success(it)
                    }

                    ApiResponse.Error(result.meta.message)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun applyAsMentor(
        request: ApplyAsMentorRequest
    ): ApiResponse<Unit> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.applyAsMentor(
                    request = request
                )

                if (result.meta.success) {
                    ApiResponse.Success(Unit)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }
}