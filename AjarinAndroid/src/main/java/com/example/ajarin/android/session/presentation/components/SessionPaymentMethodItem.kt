package com.example.ajarin.android.session.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.booking.presentation.helper.GetPaymentMethodImage
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.presentation.booking.PaymentMethod

@Composable
fun SessionPaymentMethodItem(
    modifier: Modifier = Modifier,
    method: PaymentMethod?
) {
    val imageResId = GetPaymentMethodImage
        .getImageResId(method?.id ?: "")

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .size(50.dp),
            painter = painterResource(id = imageResId),
            contentDescription = "Payment Method Logo",
            contentScale = ContentScale.Fit
        )

        Text(
            text = method?.name ?: "loading...",
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Preview
@Composable
private fun PaymentMethodPreview() {
    AjarinTheme {
        SessionPaymentMethodItem(
            method = PaymentMethod(
                id = "0",
                name = "Dana"
            )
        )
    }
}