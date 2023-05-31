package com.example.ajarin.android.booking.presentation.helper

import com.example.ajarin.android.R

object GetPaymentMethodImage {
    fun getImageResId(id: String): Int {
        return when(id) {
            "0" -> R.drawable.dana_logo
            "1" -> R.drawable.gopay_logo
            "2" -> R.drawable.shopee_logo
            "3" -> R.drawable.ovo_logo
            else -> R.drawable.ic_no_picture
        }
    }
}