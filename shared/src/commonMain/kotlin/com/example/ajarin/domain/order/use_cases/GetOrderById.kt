package com.example.ajarin.domain.order.use_cases

import com.example.ajarin.domain.order.model.Order
import com.example.ajarin.domain.order.repository.OrderRepository
import com.example.ajarin.domain.utils.Resource

class GetOrderById(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(
        id: String
    ): Resource<Order> {
        return repository
            .getOrderById(
                id = id
            )
    }
}