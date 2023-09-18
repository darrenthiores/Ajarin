package com.example.ajarin.android.mentor_profile.presentation.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.android.mentor_profile.presentation.components.MentorProfileCourseItem
import com.example.ajarin.presentation.home.Mentor
import com.example.ajarin.presentation.home.dummyMentors
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun MentorProfileAboutSection(
    modifier: Modifier = Modifier,
    mentor: Mentor?
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Education",
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = mentor?.education ?: "loading...",
                style = MaterialTheme.typography.subtitle1
            )
        }

        item {
            Text(
                text = "Courses",
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth(),
                crossAxisSpacing = 8.dp,
                mainAxisSpacing = 8.dp
            ) {
                mentor?.courses?.forEach { course ->
                    MentorProfileCourseItem(
                        text = course.name
                    )
                }
            }
        }

        item {
            Text(
                text = "Rate",
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = mentor?.price?.let { "Rp. ${mentor.price} / Session" } ?: "loading...",
                style = MaterialTheme.typography.subtitle1
            )
        }

        item {
            Text(
                text = "Description",
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            val dummyDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

            Text(
                text = dummyDescription,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Preview
@Composable
private fun MentorProfileAboutSectionPreview() {
    AjarinTheme {
        MentorProfileAboutSection(
            mentor = dummyMentors[0]
        )
    }
}