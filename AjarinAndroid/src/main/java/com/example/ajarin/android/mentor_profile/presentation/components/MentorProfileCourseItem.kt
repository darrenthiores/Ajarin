package com.example.ajarin.android.mentor_profile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme

@Composable
fun MentorProfileCourseItem(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        modifier = modifier
            .background(
                color = MaterialTheme.colors.primary.copy(alpha = 0.2f),
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        style = MaterialTheme.typography.subtitle1.copy(
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center
        )
    )
}

@Preview
@Composable
private fun MentorProfileCourseItemPreview() {
    AjarinTheme {
        MentorProfileCourseItem(text = "test")
    }
}