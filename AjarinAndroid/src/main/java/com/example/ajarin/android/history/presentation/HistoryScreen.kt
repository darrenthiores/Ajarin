package com.example.ajarin.android.history.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.ajarin.android.history.presentation.components.HistoryHeader
import com.example.ajarin.android.history.presentation.sections.MentorHistorySection
import com.example.ajarin.android.history.presentation.sections.UserHistorySection
import com.example.ajarin.domain.order.model.Order
import com.example.ajarin.presentation.history.HistoryState
import com.example.ajarin.presentation.history.HistoryTab
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HistoryScreen(
    state: HistoryState,
    userOrders: LazyPagingItems<Order>,
    mentorOrders: LazyPagingItems<Order>,
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
                isMentor = state.isMentor,
                pagerState = pagerState,
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        if(state.isMentor) {
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
                            orders = userOrders,
                            onItemClick = onUserClick,
                            onReviewClick = onReviewClick
                        )
                    }
                    1 -> {
                        MentorHistorySection(
                            orders = mentorOrders,
                            onItemClick =onMentorClick
                        )
                    }
                }
            }
        } else {
            UserHistorySection(
                modifier = Modifier
                    .padding(paddingValues),
                orders = userOrders,
                onItemClick = onUserClick,
                onReviewClick = onReviewClick
            )
        }
    }
}