package com.example.ajarin.android.core_ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ClickableField(
    title: String,
    value: String?,
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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = value ?: title,
                style = MaterialTheme.typography.body1.copy(
                    color = if (value != null) MaterialTheme.colors.primary else Color.Gray
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

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
fun ClickableFieldPreview() {
    AjarinTheme {
        ClickableField(
            title = "Date Started",
            value = null,
            onClick = {  }
        )
    }
}