package com.example.ajarin.android.history.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ajarin.android.core_ui.theme.AjarinTheme
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
    isMentor: Boolean = true,
    onUserClick: (String, String) -> Unit,
    onMentorClick: (String, String) -> Unit,
    onReviewClick: (String) -> Unit,
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
                            onItemClick = onUserClick,
                            onReviewClick = onReviewClick
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
                onItemClick = onUserClick,
                onReviewClick = onReviewClick
            )
        }
    }
}

@Preview
@Composable
private fun HistoryScreenPreview() {
    AjarinTheme {
        HistoryScreen(
            state = HistoryState(),
            isMentor = true,
            onUserClick = { _, _ -> },
            onMentorClick = { _, _ -> },
            onBackClick = {  },
            onReviewClick = {  }
        )
    }
}