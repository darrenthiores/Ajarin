package com.example.ajarin.data.order.remote.service

import com.example.ajarin.data.core.remote.dto.request.IdRequest
import com.example.ajarin.data.core.remote.dto.response.BaseResponse
import com.example.ajarin.data.order.remote.dto.request.CreateOrderRequest
import com.example.ajarin.data.order.remote.dto.request.UpdateOrderRequest
import com.example.ajarin.data.order.remote.dto.response.OrderResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class KtorOrderService(
    private val client: HttpClient
): OrderService {
    override suspend fun getUserOrders(page: Int): BaseResponse<List<OrderResponse>> {
        val result = client.get {
            url(OrderService.GET_USER_ORDER_URL + "/$page")
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    override suspend fun getMentorOrders(page: Int): BaseResponse<List<OrderResponse>> {
        val result = client.get {
            url(OrderService.GET_MENTOR_ORDER_URL + "/$page")
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    override suspend fun getOrderById(request: IdRequest): BaseResponse<OrderResponse> {
        val result = client.post {
            url(OrderService.ORDER_URL)
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

    override suspend fun createOrder(request: CreateOrderRequest): BaseResponse<Any?> {
        val result = client.post {
            url(OrderService.CREATE_ORDER_URL)
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

    override suspend fun updateOrder(request: UpdateOrderRequest): BaseResponse<Any?> {
        val result = client.post {
            url(OrderService.UPDATE_ORDER_URL)
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

}