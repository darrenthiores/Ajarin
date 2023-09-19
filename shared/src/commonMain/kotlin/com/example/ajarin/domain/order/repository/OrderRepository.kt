package com.example.ajarin.domain.order.repository

import com.example.ajarin.domain.order.model.Order
import com.example.ajarin.domain.utils.Resource

interface OrderRepository {
    suspend fun getUserOrders(
        page: Int
    ): Resource<List<Order>>
    suspend fun getMentorOrders(
        page: Int
    ): Resource<List<Order>>
    suspend fun getOrderById(
        id: String
    ): Resource<Order>
    suspend fun createOrder(
        mentorId: String,
        courseId: String,
        date: Long,
        sessionId: String,
        paymentMethodId: String
    ): Resource<Unit>

    suspend fun updateOrder(
        id: String,
        mainLink: String?,
        backupLink: String?,
        materialLink: String?,
        status: String
    ): Resource<Unit>

}