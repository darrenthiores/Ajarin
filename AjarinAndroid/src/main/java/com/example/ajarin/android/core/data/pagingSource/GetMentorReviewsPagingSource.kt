package com.example.ajarin.android.core.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ajarin.android.core.utils.CustomException
import com.example.ajarin.data.core.remote.dto.request.IdRequest
import com.example.ajarin.data.core.utils.ApiResponse
import com.example.ajarin.data.review.mapper.toReview
import com.example.ajarin.data.review.remote.ReviewRemoteDataSource
import com.example.ajarin.domain.review.model.Review
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class GetMentorReviewsPagingSource(
    private val request: IdRequest,
    private val remoteDataSource: ReviewRemoteDataSource
): PagingSource<Int, Review>() {

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        return withContext(Dispatchers.IO) {
            val page = params.key ?: 1

            Timber.d("ON GET MENTOR REVIEWS: $page")
            try {
                val response = remoteDataSource.getMentorReviews(
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
                        throw CustomException("ON GET MENTOR REVIEWS: ${response.errorMessage}")
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
                                     it.toReview()
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