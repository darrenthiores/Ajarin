package com.example.ajarin.data.order.repository

import com.example.ajarin.data.core.remote.dto.request.IdRequest
import com.example.ajarin.data.core.utils.ApiResponse
import com.example.ajarin.data.order.mapper.toOrder
import com.example.ajarin.data.order.remote.OrderRemoteDataSource
import com.example.ajarin.data.order.remote.dto.request.CreateOrderRequest
import com.example.ajarin.data.order.remote.dto.request.UpdateOrderRequest
import com.example.ajarin.domain.order.model.Order
import com.example.ajarin.domain.order.repository.OrderRepository
import com.example.ajarin.domain.utils.Resource

class OrderRepositoryImpl(
    private val remoteDataSource: OrderRemoteDataSource
): OrderRepository {
    override suspend fun getUserOrders(
        page: Int
    ): Resource<List<Order>> {
        val result = remoteDataSource.getUserOrders(
            page = page
        )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(
                result.data.map { it.toOrder() }
            )
        }
    }

    override suspend fun getMentorOrders(page: Int): Resource<List<Order>> {
        val result = remoteDataSource.getMentorOrders(
            page = page
        )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(
                result.data.map { it.toOrder() }
            )
        }
    }

    override suspend fun getOrderById(id: String): Resource<Order> {
        val result = remoteDataSource.getOrderById(
            request = IdRequest(
                id = id
            )
        )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(
                result.data.toOrder()
            )
        }
    }

    override suspend fun createOrder(
        mentorId: String,
        courseId: String,
        date: Long,
        sessionId: String,
        paymentMethodId: String
    ): Resource<Unit> {
        val result = remoteDataSource.createOrder(
            request = CreateOrderRequest(
                mentorId = mentorId,
                courseId = courseId,
                date = date,
                sessionId = sessionId,
                paymentMethodId = paymentMethodId
            )
        )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(
                result.data
            )
        }
    }

    override suspend fun updateOrder(
        id: String,
        mainLink: String?,
        backupLink: String?,
        materialLink: String?,
        status: String
    ): Resource<Unit> {
        val result = remoteDataSource.updateOrder(
            request = UpdateOrderRequest(
                id = id,
                mainLink = mainLink,
                backupLink = backupLink,
                materialLink = materialLink,
                status = status
            )
        )

        return when (result) {
            ApiResponse.Empty -> Resource.Error("Unknown Error")
            is ApiResponse.Error -> Resource.Error(result.errorMessage)
            is ApiResponse.Success -> Resource.Success(
                result.data
            )
        }
    }
}