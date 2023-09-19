package com.example.ajarin.android.search_mentor.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.domain.core.model.Course

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CourseCard(
    modifier: Modifier = Modifier,
    course: Course,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(2 / 1f),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = colors[(course.id.toIntOrNull() ?: 1) - 1],
        elevation = 5.dp,
        onClick = onClick
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp),
                text = course.name,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.background
                )
            )
        }
    }
}

@Preview
@Composable
private fun CourseCardPreview() {
    AjarinTheme {
        CourseCard(
            course = Course(
                id = "0",
                name = "Matematika"
            )
        ) {

        }
    }
}

val colors = listOf(
    Color(0xFF35c759),
    Color(0xFF31ade6),
    Color(0xFF007aff),
    Color(0xFF5856d7),
    Color(0xFF03c7be),
    Color(0xFFff2c55),
    Color(0xFFff9500),
    Color(0xFFffcc02),
    Color(0xFF30b1c7),
    Color(0xFFff3c2f)
)