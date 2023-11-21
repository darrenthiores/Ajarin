package com.example.ajarin.android.core_ui.helper

import com.example.ajarin.android.R
import com.example.ajarin.domain.core.model.Course

fun Course.getIconId(): Int {
    return when(id) {
        "1" -> R.drawable.bindonesia
        "2" -> R.drawable.biology
        "3" -> R.drawable.fisika
        "4" -> R.drawable.kimia
        "5" -> R.drawable.matematika
        "6" -> R.drawable.economic
        "7" -> R.drawable.geografi
        "8" -> R.drawable.akutansi
        "9" -> R.drawable.sejarah
        "10" -> R.drawable.sosiologi
        else -> R.drawable.ic_no_picture
    }
}