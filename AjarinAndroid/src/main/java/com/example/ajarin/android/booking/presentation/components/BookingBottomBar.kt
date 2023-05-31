package com.example.ajarin.android.booking.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.ajarin.android.core_ui.components.PrimaryButton
import com.example.ajarin.android.core_ui.theme.AjarinTheme

@Composable
fun BookingBottomBar(
    modifier: Modifier = Modifier,
    total: String,
    onPay: () -> Unit
) {
    BottomAppBar(
        modifier = modifier
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Text(
            text = "Rp. $total",
            style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.weight(1f))
        
        PrimaryButton(
            modifier = Modifier
                .weight(1f),
            text = "Pay",
            onClick = onPay
        )
    }
}

@Preview
@Composable
private fun BookingBottomBarPreview() {
    AjarinTheme {
        BookingBottomBar(total = "10000") {

        }
    }
}