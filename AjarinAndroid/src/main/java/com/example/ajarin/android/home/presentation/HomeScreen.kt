package com.example.ajarin.android.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.ajarin.android.R
import com.example.ajarin.android.core_ui.cards.FixedMentorCard
import com.example.ajarin.android.home.presentation.components.CoursesList
import com.example.ajarin.android.home.presentation.components.HomeHeader
import com.example.ajarin.domain.core.model.Course
import com.example.ajarin.domain.mentor.model.Mentor
import com.example.ajarin.domain.mentor.model.dummyMentors
import com.example.ajarin.presentation.home.HomeEvent
import com.example.ajarin.presentation.home.HomeState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    state: HomeState,
    mentors: LazyPagingItems<Mentor>,
    searchMentors: LazyPagingItems<Mentor>,
    onEvent: (HomeEvent) -> Unit,
    onCourseClick: (Course) -> Unit,
    onMentorClick: (String) -> Unit,
    onMessageClick: () -> Unit
) {
    val listState = rememberLazyListState()
    val topPickListState = rememberLazyListState()
    val recommendationListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = screenWidth / 2.25

    Scaffold(
        topBar = {
            HomeHeader(
                title = "Studify",
                unreadMessageCount = state.unreadMessageCount,
                onMessageClick = onMessageClick
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(
                vertical = 24.dp
            ),
            state = listState
        ) {
            item {
                HorizontalPager(
                    count = 3,
                    contentPadding = PaddingValues(
                        horizontal = 24.dp
                    ),
                    itemSpacing = 12.dp
                ) { index ->
                    val painter = when (index) {
                        0 -> R.drawable.car_1
                        1 -> R.drawable.car_2
                        2 -> R.drawable.car_3
                        else -> R.drawable.ic_no_picture
                    }

                    Image(
                        modifier = Modifier
                            .width(screenWidth.dp)
                            .height(screenHeight.dp)
                            .clip(
                                RoundedCornerShape(8.dp)
                            ),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = painter),
                        contentDescription = null
                    )
                }
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp
                        ),
                    text = "Courses",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                CoursesList(
                    courses = state.courses,
                    onClick = onCourseClick
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp
                        ),
                    text = "Top Picks",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    contentPadding = PaddingValues(
                        horizontal = 24.dp
                    ),
                    state = topPickListState
                ) {
//                    items(
//                        items = mentors,
//                        key = { mentor -> mentor.id }
//                    ) { mentor ->
//                        mentor?.let {
//                            FixedMentorCard(
//                                mentor = mentor
//                            ) {
//                                onMentorClick(mentor.id)
//                            }
//                        }
//                    }
                    items(
                        items = dummyMentors,
                        key = { mentor -> mentor.id }
                    ) { mentor ->
                        FixedMentorCard(
                            mentor = mentor
                        ) {
                            onMentorClick(mentor.id)
                        }
                    }
                }
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp
                        ),
                    text = "Our Recommendation",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    contentPadding = PaddingValues(
                        horizontal = 24.dp
                    ),
                    state = recommendationListState
                ) {
                    items(
                        items = dummyMentors,
                        key = { mentor -> mentor.id }
                    ) { mentor ->
                        FixedMentorCard(
                            mentor = mentor
                        ) {
                            onMentorClick(mentor.id)
                        }
                    }
                }
            }
        }
    }
}