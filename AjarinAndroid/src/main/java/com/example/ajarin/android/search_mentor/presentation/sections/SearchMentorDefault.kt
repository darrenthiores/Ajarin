package com.example.ajarin.android.search_mentor.presentation.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.android.search_mentor.presentation.components.CourseCard
import com.example.ajarin.home.presentation.Course
import com.example.ajarin.home.presentation.allCourses

@Composable
fun SearchMentorDefault(
    modifier: Modifier = Modifier,
    courses: List<Course>,
    onCourseClick: (Course) -> Unit,
    state: LazyGridState
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .fillMaxSize(),
        state = state
    ) {
        items(
            items = courses,
            key = { course -> course.id }
        ) { course ->
            CourseCard(
                course = course
            ) {
                onCourseClick(course)
            }
        }
    }
}

@Preview
@Composable
private fun SearchMentorDefaultPreview() {
    AjarinTheme {
        SearchMentorDefault(
            courses = allCourses,
            onCourseClick = {  },
            state = rememberLazyGridState()
        )
    }
}