package com.example.ajarin.android.core_ui.helper

import com.example.ajarin.android.R
import com.example.ajarin.presentation.bankAccount.Bank

fun Bank.getImageId(): Int {
    return when(bankId) {
        "1" -> R.drawable.bca_icon
        "2" -> R.drawable.bni_icon
        "3" -> R.drawable.bri_icon
        "4" -> R.drawable.mandiri_icon
        "5" -> R.drawable.bsi_icon
        "6" -> R.drawable.commbank_icon
        else -> R.drawable.ic_no_picture
    }
}