package com.example.ajarin.android.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.ajarin.android.core.data.pagingSource.GetMentorOrdersPagingSource
import com.example.ajarin.android.core.data.pagingSource.GetMentorReviewsPagingSource
import com.example.ajarin.android.core.data.pagingSource.GetMentorsPagingSource
import com.example.ajarin.android.core.data.pagingSource.GetUserOrdersPagingSource
import com.example.ajarin.android.core.data.pagingSource.SearchMentorsPagingSource
import com.example.ajarin.android.core.domain.repository.CoreRepository
import com.example.ajarin.data.core.remote.dto.request.IdRequest
import com.example.ajarin.data.mentor.remote.MentorRemoteDataSource
import com.example.ajarin.data.mentor.remote.dto.request.SearchMentorRequest
import com.example.ajarin.data.order.remote.OrderRemoteDataSource
import com.example.ajarin.data.review.remote.ReviewRemoteDataSource
import com.example.ajarin.domain.mentor.model.Mentor
import com.example.ajarin.domain.order.model.Order
import com.example.ajarin.domain.review.model.Review
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoreRepositoryImpl @Inject constructor(
    private val mentorRemoteDataSource: MentorRemoteDataSource,
    private val orderRemoteDataSource: OrderRemoteDataSource,
    private val reviewRemoteDataSource: ReviewRemoteDataSource
): CoreRepository {
    override fun getMentors(): Flow<PagingData<Mentor>> {
        return Pager(
            config = PagingConfig(
                initialLoadSize = 45,
                pageSize = 15,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                GetMentorsPagingSource(
                    remoteDataSource = mentorRemoteDataSource
                )
            }
        ).flow
    }

    override fun searchMentors(
        name: String,
        education: String,
        rating: Double,
        courseId: String,
        price: String,
    ): Flow<PagingData<Mentor>> {
        return Pager(
            config = PagingConfig(
                initialLoadSize = 45,
                pageSize = 15,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                SearchMentorsPagingSource(
                    request = SearchMentorRequest(
                        name = name,
                        education = education,
                        rating = rating,
                        courseId = courseId,
                        price = price
                    ),
                    remoteDataSource = mentorRemoteDataSource
                )
            }
        ).flow
    }

    override fun getUserOrders(): Flow<PagingData<Order>> {
        return Pager(
            config = PagingConfig(
                initialLoadSize = 45,
                pageSize = 15,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                GetUserOrdersPagingSource(
                    remoteDataSource = orderRemoteDataSource
                )
            }
        ).flow
    }

    override fun getMentorOrders(): Flow<PagingData<Order>> {
        return Pager(
            config = PagingConfig(
                initialLoadSize = 45,
                pageSize = 15,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                GetMentorOrdersPagingSource(
                    remoteDataSource = orderRemoteDataSource
                )
            }
        ).flow
    }

    override fun getMentorReviews(
        id: String
    ): Flow<PagingData<Review>> {
        return Pager(
            config = PagingConfig(
                initialLoadSize = 45,
                pageSize = 15,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                GetMentorReviewsPagingSource(
                    request = IdRequest(
                        id = id
                    ),
                    remoteDataSource = reviewRemoteDataSource
                )
            }
        ).flow
    }
}