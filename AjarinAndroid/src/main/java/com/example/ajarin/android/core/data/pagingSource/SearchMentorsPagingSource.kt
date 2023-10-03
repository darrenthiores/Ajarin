package com.example.ajarin.android.core.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ajarin.android.core.utils.CustomException
import com.example.ajarin.data.core.utils.ApiResponse
import com.example.ajarin.data.mentor.mapper.toMentor
import com.example.ajarin.data.mentor.remote.MentorRemoteDataSource
import com.example.ajarin.data.mentor.remote.dto.request.SearchMentorRequest
import com.example.ajarin.domain.mentor.model.Mentor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class SearchMentorsPagingSource(
    private val request: SearchMentorRequest,
    private val remoteDataSource: MentorRemoteDataSource
): PagingSource<Int, Mentor>() {

    override fun getRefreshKey(state: PagingState<Int, Mentor>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Mentor> {
        return withContext(Dispatchers.IO) {
            val page = params.key ?: 1

            Timber.d("ON SEARCH MENTORS: $page")

            try {
                val response = remoteDataSource.searchMentor(
                    request = request,
                    page = page
                )

                when (response) {
                    ApiResponse.Empty -> {
                        LoadResult.Page(
                            data = emptyList(),
                            prevKey = if(page==1) null else page.minus(1),
                            nextKey = null
                        )
                    }
                    is ApiResponse.Error -> {
                        throw CustomException("ON SEARCH MENTORS ERROR: ${response.errorMessage}")
                    }
                    is ApiResponse.Success -> {
                        val mentors = response.data

                        val nextKey = if(mentors.isEmpty()) {
                            null
                        } else {
                            page.plus(1) //+ (params.loadSize / LOAD_SIZE)
                        }

                        LoadResult.Page(
                            data = mentors
                                .map {
                                     it.toMentor()
                                },
                            prevKey = if(page==1) null else page.minus(1),
                            nextKey = nextKey
                        )
                    }
                }
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }
}