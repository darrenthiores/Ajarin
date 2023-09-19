package com.example.ajarin.data.order.remote.dto.response

import com.example.ajarin.data.core.remote.dto.response.Course
import com.example.ajarin.data.core.remote.dto.response.PaymentMethod
import com.example.ajarin.data.core.remote.dto.response.Session
import kotlinx.serialization.Serializable

@Serializable
data class OrderResponse(
    val id: String,
    val mentorId: String,
    val mentorName: String,
    val mentorImgUrl: String,
    val userId: String,
    val userName: String,
    val userImgUrl: String,
    val course: Course,
    val schedule: Session,
    val date: Long,
    val mentorPrice: String,
    val totalPrice: String,
    val status: String,
    val paymentMethod: PaymentMethod
)
