package com.example.ajarin.android.history.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ajarin.android.history.presentation.components.HistoryHeader
import com.example.ajarin.android.history.presentation.sections.MentorHistorySection
import com.example.ajarin.android.history.presentation.sections.UserHistorySection
import com.example.ajarin.history.presentation.HistoryState
import com.example.ajarin.history.presentation.HistoryTab
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HistoryScreen(
    state: HistoryState,
    isMentor: Boolean,
    onUserClick: (String, String) -> Unit,
    onMentorClick: (String, String) -> Unit,
    onBackClick: () -> Unit
) {
    val pagerState = rememberPagerState()

    Scaffold(
        topBar = {
            HistoryHeader(
                title = "History",
                isMentor = isMentor,
                pagerState = pagerState,
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        if(isMentor) {
            HorizontalPager(
                count = HistoryTab.values().size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) { page ->
                when(page) {
                    0 -> {
                        UserHistorySection(
                            state = state,
                            onItemClick = onUserClick
                        )
                    }
                    1 -> {
                        MentorHistorySection(
                            state = state,
                            onItemClick =onMentorClick
                        )
                    }
                }
            }
        } else {
            UserHistorySection(
                modifier = Modifier
                    .padding(paddingValues),
                state = state,
                onItemClick = onUserClick
            )
        }
    }
}