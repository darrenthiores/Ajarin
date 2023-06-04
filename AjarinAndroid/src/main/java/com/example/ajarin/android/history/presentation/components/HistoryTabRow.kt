package com.example.ajarin.android.history.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.history.presentation.HistoryTab
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun HistoryTabRow(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            Box(
                modifier = Modifier
                    .pagerTabIndicatorOffset(pagerState, tabPositions)
                    .height(2.dp)
                    .background(color = MaterialTheme.colors.primary)
            )
        },
        divider = {  },
        backgroundColor = Color.Transparent,
        contentColor = MaterialTheme.colors.primary
    ) {
        HistoryTab.values().forEachIndexed { index, screen ->
            Tab(
                text = { Text(screen.name) },
                selected = pagerState.currentPage == index,
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun HistoryTabRowPreview() {
    AjarinTheme {
        HistoryTabRow(
            pagerState = rememberPagerState()
        )
    }
}