package com.example.ajarin.android.mentor_profile.presentation

import android.content.Intent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ajarin.android.R
import com.example.ajarin.android.core_ui.components.PrimaryButton
import com.example.ajarin.android.core_ui.navigation.Route
import com.example.ajarin.android.mentor_profile.presentation.components.MentorProfileHeader
import com.example.ajarin.android.mentor_profile.presentation.components.MentorProfileTabRow
import com.example.ajarin.android.mentor_profile.presentation.sections.MentorProfileAboutSection
import com.example.ajarin.android.mentor_profile.presentation.sections.MentorProfileReviewSection
import com.example.ajarin.android.mentor_profile.presentation.sections.MentorProfileScheduleSection
import com.example.ajarin.domain.order.model.sessions
import com.example.ajarin.domain.review.model.Review
import com.example.ajarin.presentation.mentorProfile.MentorProfileState
import com.example.ajarin.presentation.mentorProfile.MentorProfileTab
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MentorProfileScreen(
    state: MentorProfileState,
    reviews: LazyPagingItems<Review>,
    onBackClick: () -> Unit,
    onBookClick: () -> Unit,
    onChatClick: () -> Unit
) {
    val pagerState = rememberPagerState()
    val context = LocalContext.current
    val mentor = state.mentor

    Scaffold(
        topBar = {
            MentorProfileHeader(
                title = mentor?.name ?: "Mentor Profile",
                onBackClick = onBackClick,
                onChatClick = onChatClick,
                onShareClick = {
                    mentor?.let {
                        val uri = "https://www.ajarin.com/" + Route.MentorProfile.name + "/${mentor.id}"
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(
                                Intent.EXTRA_TEXT,
                                uri
                            )
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)

                        context.startActivity(shareIntent)
                    }
                }
            )
        },
        floatingActionButton = {
            PrimaryButton(
                text = "Book"
            ) {
                onBookClick()
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        BoxWithConstraints {
            val screenHeight = maxHeight
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(state = scrollState)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(4 / 5f),
                    contentScale = ContentScale.Crop,
                    model = ImageRequest
                        .Builder(context)
                        .data(mentor?.photoUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.ic_no_picture),
                    contentDescription = mentor?.id + " photo"
                )

                Column(
                    modifier = Modifier.height(screenHeight)
                ) {
                    MentorProfileTabRow(
                        pagerState = pagerState
                    )

                    HorizontalPager(
                        count = MentorProfileTab.values().size,
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .nestedScroll(
                                remember {
                                    object : NestedScrollConnection {
                                        override fun onPreScroll(
                                            available: Offset,
                                            source: NestedScrollSource
                                        ): Offset {
                                            return if (available.y > 0) Offset.Zero else Offset(
                                                x = 0f,
                                                y = -scrollState.dispatchRawDelta(-available.y)
                                            )
                                        }
                                    }
                                }
                            )
                    ) { page ->
                        when (page) {
                            0 -> {
                                MentorProfileAboutSection(
                                    modifier = Modifier,
                                    mentor = mentor
                                )
                            }

                            1 -> {
                                MentorProfileScheduleSection(
                                    sessions = sessions
                                )
                            }

                            2 -> {
                                MentorProfileReviewSection(
                                    modifier = Modifier,
                                    reviews = reviews
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun MentorProfileScreenPreview() {
//    AjarinTheme {
//        MentorProfileScreen(
//            state = MentorProfileState(),
//            onBackClick = {  },
//            onBookClick = {  },
//            onChatClick = {  }
//        )
//    }
//}