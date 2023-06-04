package com.example.ajarin.android.history.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HistoryHeader(
    title: String,
    isMentor: Boolean,
    pagerState: PagerState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = 0.dp,
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ChevronLeft,
                        contentDescription = "Back"
                    )
                }

                Text(
                    text = title,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            if (isMentor) {
                HistoryTabRow(
                    pagerState = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
private fun HistoryHeaderPreview() {
    AjarinTheme {
        HistoryHeader(
            title = "First Aid",
            isMentor = true,
            pagerState = rememberPagerState(),
            onBackClick = {  }
        )
    }
}