package com.example.ajarin.domain.order.use_cases

import com.example.ajarin.domain.order.repository.OrderRepository
import com.example.ajarin.domain.utils.Resource

class CreateOrder(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(
        mentorId: String,
        courseId: String,
        date: Long,
        sessionId: String,
        paymentMethodId: String
    ): Resource<Unit> {
        return repository
            .createOrder(
                mentorId = mentorId,
                courseId = courseId,
                date = date,
                sessionId = sessionId,
                paymentMethodId = paymentMethodId
            )
    }
}