package com.example.ajarin.android.search_mentor.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.android.search_mentor.presentation.components.FilterMentorSheet
import com.example.ajarin.android.search_mentor.presentation.components.SearchTextField
import com.example.ajarin.android.search_mentor.presentation.sections.SearchMentorDefault
import com.example.ajarin.android.search_mentor.presentation.sections.SearchMentorResult
import com.example.ajarin.searchMentor.presentation.SearchMentorEvent
import com.example.ajarin.searchMentor.presentation.SearchMentorState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchMentorScreen(
    state: SearchMentorState,
    onEvent: (SearchMentorEvent) -> Unit,
    onMentorClick: (String) -> Unit
) {
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val coroutineScope = rememberCoroutineScope()

    val defaultListState = rememberLazyGridState()
    val resultListState = rememberLazyGridState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            FilterMentorSheet(
                state = state,
                onEvent = { event ->
                    if (event == SearchMentorEvent.OnApply) {
                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.collapse()
                        }
                    }

                    onEvent(event)
                }
            )
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetPeekHeight = 0.dp
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (state.mentors.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            onEvent(
                                SearchMentorEvent.OnSearchChange("")
                            )

                            onEvent(
                                SearchMentorEvent.OnReset
                            )

                            onEvent(
                                SearchMentorEvent.OnApply
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIos,
                            contentDescription = "Back"
                        )
                    }
                }

                SearchTextField(
                    modifier = Modifier
                        .weight(1f),
                    text = state.searchText,
                    onTextChange = {
                        onEvent(
                            SearchMentorEvent.OnSearchChange(it)
                        )
                    },
                    onSearch = {
                        onEvent(
                            SearchMentorEvent.OnSearch
                        )
                    }
                )

                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Tune,
                        contentDescription = "Filter"
                    )
                }
            }

            if (state.mentors.isNotEmpty()) {
                SearchMentorResult(
                    mentors = state.mentors,
                    state = resultListState,
                    onMentorClick = onMentorClick
                )
            } else {
                SearchMentorDefault(
                    courses = state.courses,
                    onCourseClick = {
                        onEvent(
                            SearchMentorEvent.OnPickCourse(it)
                        )

                        onEvent(
                            SearchMentorEvent.OnApply
                        )
                    },
                    state = defaultListState
                )
            }
        }
    }
}

@Preview
@Composable
private fun SearchMentorScreenPreview() {
    AjarinTheme {
        SearchMentorScreen(
            state = SearchMentorState(),
            onEvent = {  },
            onMentorClick = {  }
        )
    }
}