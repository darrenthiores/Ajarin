package com.example.ajarin.android.core.domain.repository

import androidx.paging.PagingData
import com.example.ajarin.domain.mentor.model.Mentor
import com.example.ajarin.domain.order.model.Order
import com.example.ajarin.domain.review.model.Review
import kotlinx.coroutines.flow.Flow

interface CoreRepository {
    fun getMentors(): Flow<PagingData<Mentor>>
    fun searchMentors(
        name: String,
        education: String,
        rating: Double,
        courseId: String,
        price: String
    ): Flow<PagingData<Mentor>>
    fun getUserOrders(): Flow<PagingData<Order>>
    fun getMentorOrders(): Flow<PagingData<Order>>
    fun getMentorReviews(
        id: String
    ): Flow<PagingData<Review>>
}