package com.example.ajarin.domain.core.model

data class PaymentMethod(
    val id: String,
    val name: String
)

val paymentMethods = listOf(
    PaymentMethod(
        id = "0",
        name = "Dana"
    ),
    PaymentMethod(
        id = "1",
        name = "Gopay"
    ),
    PaymentMethod(
        id = "2",
        name = "Shopee Pay"
    ),
    PaymentMethod(
        id = "3",
        name = "Ovo"
    )
)