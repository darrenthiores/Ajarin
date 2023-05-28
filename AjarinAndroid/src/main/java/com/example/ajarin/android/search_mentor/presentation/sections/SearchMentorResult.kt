package com.example.ajarin.android.search_mentor.presentation.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.cards.MentorCard
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.home.presentation.Mentor
import com.example.ajarin.home.presentation.dummyMentors

@Composable
fun SearchMentorResult(
    modifier: Modifier = Modifier,
    mentors: List<Mentor>,
    state: LazyGridState,
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = state
    ) {
        items(
            items = mentors,
            key = { mentor -> mentor.id }
        ) { mentor ->
            MentorCard(
                mentor = mentor
            ) {

            }
        }
    }
}

@Preview
@Composable
private fun SearchMentorResultPreview() {
    AjarinTheme {
        SearchMentorResult(
            mentors = dummyMentors,
            state = rememberLazyGridState()
        )
    }
}