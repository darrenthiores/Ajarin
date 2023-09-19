package com.example.ajarin.data.order.mapper

import com.example.ajarin.data.order.remote.dto.response.OrderResponse
import com.example.ajarin.domain.core.model.Course
import com.example.ajarin.domain.core.model.PaymentMethod
import com.example.ajarin.domain.order.model.Order
import com.example.ajarin.domain.order.model.Session
import com.example.ajarin.domain.utils.LocalDateConverter

fun OrderResponse.toOrder(): Order {
    return Order(
        id = id,
        mentorId = mentorId,
        mentorName = mentorName,
        mentorImgUrl = mentorImgUrl,
        userId = userId,
        userName = userName,
        userImgUrl = userImgUrl,
        course = Course(
            id = course.id,
            name = course.name
        ),
        schedule = Session(
            id = schedule.id,
            time = schedule.time
        ),
        date = LocalDateConverter.timestampToLocalDate(date),
        mentorPrice = mentorPrice,
        totalPrice = totalPrice,
        status = status,
        paymentMethod = PaymentMethod(
            id = paymentMethod.id,
            name = paymentMethod.name
        )
    )
}