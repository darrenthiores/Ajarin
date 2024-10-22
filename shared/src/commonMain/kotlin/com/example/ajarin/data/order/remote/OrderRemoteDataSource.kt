package com.example.ajarin.data.order.remote

import com.example.ajarin.data.core.remote.dto.request.IdRequest
import com.example.ajarin.data.core.utils.ApiResponse
import com.example.ajarin.data.core.utils.tryCatch
import com.example.ajarin.data.order.remote.dto.request.CreateOrderRequest
import com.example.ajarin.data.order.remote.dto.request.UpdateOrderRequest
import com.example.ajarin.data.order.remote.dto.response.OrderResponse
import com.example.ajarin.data.order.remote.service.OrderService
import com.example.ajarin.domain.core.dispatchers.DispatchersProvider
import kotlinx.coroutines.withContext

class OrderRemoteDataSource(
    private val apiService: OrderService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun getUserOrders(
        page: Int
    ): ApiResponse<List<OrderResponse>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getUserOrders(
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

    suspend fun getMentorOrders(
        page: Int
    ): ApiResponse<List<OrderResponse>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getMentorOrders(
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

    suspend fun getOrderById(
        request: IdRequest
    ): ApiResponse<OrderResponse> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getOrderById(
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

    suspend fun createOrder(
        request: CreateOrderRequest
    ): ApiResponse<Unit> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.createOrder(
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

    suspend fun updateOrder(
        request: UpdateOrderRequest
    ): ApiResponse<Unit> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.updateOrder(
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