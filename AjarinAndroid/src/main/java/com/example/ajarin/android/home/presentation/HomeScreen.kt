package com.example.ajarin.android.home.presentation

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
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ajarin.android.R
import com.example.ajarin.android.core_ui.cards.FixedMentorCard
import com.example.ajarin.android.home.presentation.components.CoursesList
import com.example.ajarin.android.home.presentation.components.HomeHeader
import com.example.ajarin.domain.mentor.model.Mentor
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
    onMentorClick: (String) -> Unit,
    onMessageClick: () -> Unit
) {
    val listState = rememberLazyGridState()
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
            )
        ) {
            item {
                HorizontalPager(
                    count = 3,
                    contentPadding = PaddingValues(
                        horizontal = 24.dp
                    ),
                    itemSpacing = 12.dp
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .width(screenWidth.dp)
                            .height(screenHeight.dp)
                            .clip(
                                RoundedCornerShape(8.dp)
                            ),
                        contentScale = ContentScale.Crop,
                        model = ImageRequest
                            .Builder(context)
                            .data("")
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(id = R.drawable.ic_no_picture),
                        error = painterResource(id = R.drawable.ic_no_picture),
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
                    onClick = {  }
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
                    )
                ) {
                    items(
                        count = mentors.itemCount
                    ) { index ->
                        mentors[index]?.let { mentor ->
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
}