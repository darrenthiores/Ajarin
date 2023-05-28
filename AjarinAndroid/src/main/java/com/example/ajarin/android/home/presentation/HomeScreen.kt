package com.example.ajarin.android.home.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.android.core_ui.cards.MentorCard
import com.example.ajarin.home.presentation.Course
import com.example.ajarin.home.presentation.HomeEvent
import com.example.ajarin.home.presentation.HomeState
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    val listState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            item {
                Text(
                    modifier = Modifier
                        .clickable {
                            coroutineScope.launch {
                                listState.scrollToItem(0)
                            }

                            onEvent(
                                HomeEvent.OnSelectCourse(
                                    Course(
                                        id = "0",
                                        name = "All"
                                    )
                                )
                            )
                        },
                    text = "All",
                    fontWeight = if(state.selectedCourse.id == "0") FontWeight.Bold else FontWeight.Normal
                )
            }

            items(
                items = state.courses,
                key = { course -> course.id }
            ) { course ->
                Text(
                    modifier = Modifier
                        .clickable {
                            coroutineScope.launch {
                                listState.scrollToItem(0)
                            }

                            onEvent(
                                HomeEvent.OnSelectCourse(
                                    course = course
                                )
                            )
                        },
                    text = "#" + course.name,
                    fontWeight = if(state.selectedCourse == course) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
        
        LazyVerticalGrid(
            modifier = Modifier
                .weight(1f),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            state = listState
        ) {
            items(
                items = state.mentors,
                key = { mentor -> mentor.id }
            ) { mentor ->
                MentorCard(
                    mentor = mentor
                ) {

                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AjarinTheme {
        Surface {
            HomeScreen(
                state = HomeState(),
                onEvent = {  }
            )
        }
    }
}