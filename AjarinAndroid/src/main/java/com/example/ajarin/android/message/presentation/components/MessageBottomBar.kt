package com.example.ajarin.android.message.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme

@Composable
fun MessageBottomBar(
    modifier: Modifier = Modifier,
    message: String,
    onMessageChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        MessageTextField(
            modifier = Modifier
                .weight(1f),
            text = message,
            onTextChange = onMessageChange
        )

        Spacer(modifier = Modifier.width(16.dp))

        Box(
            modifier = Modifier
                .height(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        onSendClick()
                    },
                imageVector = Icons.Rounded.Send,
                contentDescription = "Send Messages",
                tint = MaterialTheme.colors.primary
            )
        }
    }
}

@Preview
@Composable
private fun MessageBottomBarPreview() {
    AjarinTheme {
        MessageBottomBar(
            message = "",
            onMessageChange = {  }
        ) {

        }
    }
}