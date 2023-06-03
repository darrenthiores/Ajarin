package com.example.ajarin.android.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Message
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ajarin.android.core_ui.theme.AjarinTheme

@Composable
fun HomeHeader(
    title: String,
    unreadMessageCount: Int,
    onMessageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                ),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
            )

            HomeMessageButton(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                unreadMessageCount = unreadMessageCount,
                onMessageClick = onMessageClick
            )
        }
    }
}

@Preview
@Composable
fun CommonHeaderPreview() {
    AjarinTheme {
        HomeHeader(
            title = "First Aid",
            unreadMessageCount = 0,
            onMessageClick = {  }
        )
    }
}

@Composable
private fun HomeMessageButton(
    modifier: Modifier = Modifier,
    unreadMessageCount: Int,
    onMessageClick: () -> Unit
) {
    IconButton(
        onClick = onMessageClick,
        modifier = modifier
    ) {
        Box {
            Icon(
                imageVector = Icons.Rounded.Message,
                contentDescription = "Message Icon",
                modifier = Modifier
            )

            if (unreadMessageCount > 0) {
                val notificationText = if (unreadMessageCount > 9) "9+" else "$unreadMessageCount"

                Box(
                    modifier = Modifier
                        .offset(x = 4.dp, y = (-4).dp)
                        .size(14.dp)
                        .clip(CircleShape)
                        .background(Color.Red)
                        .align(Alignment.TopEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = notificationText,
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.background,
                            fontSize = 8.sp
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeNotificationButtonWithNotificationPreview() {
    AjarinTheme {
        HomeMessageButton(
            unreadMessageCount = 20,
            onMessageClick = {  }
        )
    }
}

@Preview
@Composable
fun HomeNotificationButtonPreview() {
    AjarinTheme {
        HomeMessageButton(
            unreadMessageCount = 0,
            onMessageClick = {  }
        )
    }
}