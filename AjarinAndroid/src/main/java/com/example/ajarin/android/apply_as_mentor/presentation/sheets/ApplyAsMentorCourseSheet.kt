package com.example.ajarin.android.apply_as_mentor.presentation.sheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.components.CommonHeader
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.domain.core.model.Course
import com.example.ajarin.domain.core.model.allCourses
import com.example.ajarin.domain.order.model.sessions

@Composable
fun ApplyAsMentorCourseSheet(
    modifier: Modifier = Modifier,
    selectedCourses: List<Course>,
    onClick: (Course) -> Unit,
    onBackClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            CommonHeader(
                title = "Select Courses",
                onBackClick = onBackClick
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        itemsIndexed(
            items = allCourses
        ) { index, course ->
            CourseItem(
                modifier = Modifier
                    .toggleable(
                        value = selectedCourses.contains(course),
                        role = Role.Checkbox,
                        onValueChange = {
                            onClick(course)
                        }
                    ),
                course = course,
                isChecked = selectedCourses.contains(course)
            )

            if (index != sessions.lastIndex) {
                Divider()
            }
        }
    }
}

@Preview
@Composable
private fun ApplyAsMentorCourseSheetPreview() {
    AjarinTheme {
        ApplyAsMentorCourseSheet(
            selectedCourses = emptyList(),
            onClick = {  },
            onBackClick = {  }
        )
    }
}

@Composable
private fun CourseItem(
    modifier: Modifier = Modifier,
    course: Course,
    isChecked: Boolean
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = course.name
        )

        Checkbox(
            checked = isChecked,
            onCheckedChange = null
        )
    }
}

@Preview
@Composable
private fun CourseItemPreview() {
    AjarinTheme {
        CourseItem(
            course = allCourses[0],
            isChecked = true
        )
    }
}