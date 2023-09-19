package com.example.ajarin.domain.order.use_cases

import com.example.ajarin.domain.order.repository.OrderRepository
import com.example.ajarin.domain.utils.Resource

class UpdateOrder(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(
        id: String,
        mainLink: String?,
        backupLink: String?,
        materialLink: String?,
        status: String
    ): Resource<Unit> {
        return repository
            .updateOrder(
                id = id,
                mainLink = mainLink,
                backupLink = backupLink,
                materialLink = materialLink,
                status = status
            )
    }
}