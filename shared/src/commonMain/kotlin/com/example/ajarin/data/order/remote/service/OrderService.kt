package com.example.ajarin.data.order.remote.service

import com.example.ajarin.data.core.remote.dto.request.IdRequest
import com.example.ajarin.data.core.remote.dto.response.BaseResponse
import com.example.ajarin.data.order.remote.dto.request.CreateOrderRequest
import com.example.ajarin.data.order.remote.dto.request.UpdateOrderRequest
import com.example.ajarin.data.order.remote.dto.response.OrderResponse

interface OrderService {
    suspend fun getUserOrders(page: Int): BaseResponse<List<OrderResponse>>
    suspend fun getMentorOrders(page: Int): BaseResponse<List<OrderResponse>>
    suspend fun getOrderById(
        request: IdRequest
    ): BaseResponse<OrderResponse>
    suspend fun createOrder(
        request: CreateOrderRequest
    ): BaseResponse<String?>
    suspend fun updateOrder(request: UpdateOrderRequest): BaseResponse<String?>

    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080"
        private const val LOCAL_BASE_URL = "http://10.0.2.2:8080"
        private const val IOS_BASE_URL = "http://0.0.0.0:8080"
        const val ORDER_URL = "$BASE_URL/order"
        const val GET_USER_ORDER_URL = "$BASE_URL/order/user"
        const val GET_MENTOR_ORDER_URL = "$BASE_URL/order/mentor"
        const val CREATE_ORDER_URL = "$BASE_URL/order/create"
        const val UPDATE_ORDER_URL = "$BASE_URL/order/update"
    }
}