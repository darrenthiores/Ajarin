package com.example.ajarin.android.search_mentor.presentation.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.ajarin.android.core_ui.cards.MentorCard
import com.example.ajarin.domain.mentor.model.Mentor

@Composable
fun SearchMentorResult(
    modifier: Modifier = Modifier,
    mentors: LazyPagingItems<Mentor>,
    state: LazyGridState,
    onMentorClick: (String) -> Unit
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
            count = mentors.itemCount
        ) { index ->
            mentors[index]?.let { mentor ->
                MentorCard(
                    mentor = mentor
                ) {
                    onMentorClick(mentor.id)
                }
            }
        }
    }
}

//@Preview
//@Composable
//private fun SearchMentorResultPreview() {
//    AjarinTheme {
//        SearchMentorResult(
//            mentors = dummyMentors,
//            state = rememberLazyGridState(),
//            onMentorClick = {  }
//        )
//    }
//}