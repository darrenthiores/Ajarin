package com.example.ajarin.android.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.helper.getIconId
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.domain.core.model.Course
import com.example.ajarin.domain.core.model.allCourses
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CoursesList(
    modifier: Modifier = Modifier,
    courses: List<Course>,
    onClick: (Course) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp / 4
    val itemWidth = (screenWidth - 24)
    val imageWidth = (itemWidth - 32)

    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp
            ),
        mainAxisSpacing = 16.dp,
        crossAxisSpacing = 16.dp
    ) {
        courses.forEach { course ->
            Column(
                modifier = Modifier
                    .width(itemWidth.dp)
                    .clickable {
                        onClick(course)
                    },
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(itemWidth.dp)
                        .clip(CircleShape)
                        .background(
                            Color.LightGray,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .size(imageWidth.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = course.getIconId()),
                        contentDescription = course.id
                    )
                }

                Text(
                    text = course.name,
                    style = MaterialTheme.typography.caption,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
private fun CoursesListPreview() {
    AjarinTheme {
        CoursesList(
            courses = allCourses,
            onClick = {  }
        )
    }
}