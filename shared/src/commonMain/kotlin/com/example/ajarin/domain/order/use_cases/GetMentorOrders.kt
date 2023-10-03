package com.example.ajarin.domain.order.use_cases

import com.example.ajarin.domain.order.model.Order
import com.example.ajarin.domain.order.repository.OrderRepository
import com.example.ajarin.domain.utils.Resource

class GetMentorOrders(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(
        page: Int
    ): List<Order> {
        val result = repository
            .getMentorOrders(
                page = page
            )

        return when (result) {
            is Resource.Error -> emptyList()
            is Resource.Loading -> emptyList()
            is Resource.Success -> result.data ?: emptyList()
        }
    }
}