package com.example.ajarin.android.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.R
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
            Image(
                modifier = Modifier
                    .size(itemWidth.dp)
                    .clip(CircleShape)
                    .clickable {
                        onClick(course)
                    },
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.ic_no_picture),
                contentDescription = course.id
            )
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