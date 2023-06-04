package com.example.ajarin.android.core.utils

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts

class GalleryPicker(
    private val contentType: String
): ActivityResultContracts.GetContent() {
    override fun createIntent(context: Context, input: String): Intent {
        super.createIntent(context, input)
        return Intent(Intent.ACTION_PICK).apply {
            type = contentType
        }
    }
}