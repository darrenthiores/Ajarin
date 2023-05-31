package com.example.ajarin.android.booking.presentation.sheets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
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
import com.example.ajarin.android.core_ui.components.CommonHeader
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.booking.presentation.PaymentMethod
import com.example.ajarin.booking.presentation.paymentMethods
import com.example.ajarin.mentorProfile.presentation.sessions

@Composable
fun BookingPaymentMethodSheet(
    modifier: Modifier = Modifier,
    onClick: (PaymentMethod) -> Unit,
    onBackClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            CommonHeader(
                title = "Pick Payment Method",
                onBackClick = onBackClick
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        itemsIndexed(
            items = paymentMethods
        ) { index, method ->
            PaymentMethodItem(
                modifier = Modifier
                    .clickable {
                        onClick(method)
                    },
                method = method
            )

            if (index != sessions.lastIndex) {
                Divider()
            }
        }
    }
}

@Preview
@Composable
private fun BookingPaymentMethodSheetPreview() {
    AjarinTheme {
        BookingPaymentMethodSheet(
            onClick = {  },
            onBackClick = {  }
        )
    }
}

@Composable
private fun PaymentMethodItem(
    modifier: Modifier = Modifier,
    method: PaymentMethod
) {
    val imageResId = GetPaymentMethodImage
        .getImageResId(method.id)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
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
            text = method.name,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Preview
@Composable
private fun ScheduleItemPreview() {
    AjarinTheme {
        PaymentMethodItem(
            method = PaymentMethod(
                id = "0",
                name = "Dana"
            )
        )
    }
}