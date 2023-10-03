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
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.ajarin.android.home.presentation.AndroidSearchMentorEvent
import com.example.ajarin.android.search_mentor.presentation.components.FilterMentorSheet
import com.example.ajarin.android.search_mentor.presentation.components.SearchTextField
import com.example.ajarin.android.search_mentor.presentation.sections.SearchMentorDefault
import com.example.ajarin.android.search_mentor.presentation.sections.SearchMentorResult
import com.example.ajarin.domain.mentor.model.Mentor
import com.example.ajarin.presentation.searchMentor.SearchMentorEvent
import com.example.ajarin.presentation.searchMentor.SearchMentorState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchMentorScreen(
    state: SearchMentorState,
    mentors: LazyPagingItems<Mentor>,
    onEvent: (SearchMentorEvent) -> Unit,
    onAndroidEvent: (AndroidSearchMentorEvent) -> Unit,
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
                if (mentors.itemCount > 0) {
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
                        onAndroidEvent(
                            AndroidSearchMentorEvent.OnSearch
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

            if (mentors.itemCount > 0) {
                SearchMentorResult(
                    mentors = mentors,
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