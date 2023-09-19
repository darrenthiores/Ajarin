package com.example.ajarin.android.booking.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.booking.presentation.helper.GetPaymentMethodImage
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.domain.core.model.PaymentMethod

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookingPaymentMethodField(
    title: String,
    value: PaymentMethod?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClick,
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            value?.let {
                val imageResId = GetPaymentMethodImage
                    .getImageResId(value.id)

                Image(
                    modifier = modifier
                        .size(50.dp),
                    painter = painterResource(id = imageResId),
                    contentDescription = "Payment Method Logo",
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.width(16.dp))
            }

            Text(
                text = value?.name ?: title,
                style = MaterialTheme.typography.body1.copy(
                    color = if (value!=null) MaterialTheme.colors.onBackground else Color.Gray
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Preview
@Composable
fun BookingPaymentMethodFieldPreview() {
    AjarinTheme {
        BookingPaymentMethodField(
            title = "Date Started",
            value = null,
            onClick = {  }
        )
    }
}