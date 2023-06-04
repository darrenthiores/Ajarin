package com.example.ajarin.android.add_review.presentation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.addReview.presentation.AddReviewEvent
import com.example.ajarin.addReview.presentation.AddReviewState
import com.example.ajarin.android.add_review.presentation.components.AddReviewHeader
import com.example.ajarin.android.add_review.presentation.components.AddReviewImages
import com.example.ajarin.android.add_review.presentation.components.AddReviewMentorItem
import com.example.ajarin.android.core.utils.GalleryPicker
import com.example.ajarin.android.core_ui.theme.AjarinTheme

@Composable
fun AddReviewScreen(
    state: AddReviewState,
    onEvent: (AddReviewEvent) -> Unit,
    images: List<Uri>,
    addImage: (Uri) -> Unit,
    removeImage: (Uri) -> Unit,
    onBackClick: () -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        GalleryPicker(contentType = "image/*")
    ) { imageUri ->
        imageUri?.let {
            addImage(it)
        }
    }

    Scaffold(
        topBar = {
            AddReviewHeader(
                title = "Add Review",
                postEnabled = !state.isPosting && state.reviewText.isNotBlank() && state.rating != 0,
                onPostClick = {
                    onEvent(
                        AddReviewEvent.OnPostReview
                    )
                },
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                AddReviewImages(
                    images = images,
                    onRemoveClick = removeImage,
                    onAddClick = {
                        launcher.launch("")
                    }
                )
            }

            item {
                Text(
                    text = "Review",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                BasicTextField(
                    value = state.reviewText,
                    onValueChange = {
                        onEvent(
                            AddReviewEvent.OnReviewChange(it)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 50.dp)
                        .padding(horizontal = 16.dp),
                    decorationBox = { innerTextField ->
                        if(state.reviewText.isEmpty()) {
                            Text(text = "Tell People Your Experience...")
                        }
                        innerTextField()
                    }
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = "Rating",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement
                        .spacedBy(8.dp),
                    verticalAlignment = Alignment
                        .CenterVertically
                ) {
                    repeat(5) { i ->
                        IconButton(
                            onClick = {
                                onEvent(
                                    AddReviewEvent.OnPickRating(i+1)
                                )
                            }
                        ) {
                            Icon(
                                imageVector = if(state.rating >= i+1) Icons.Filled.Star
                                else Icons.Outlined.Star,
                                contentDescription = "Rating ${i + 1}",
                                tint = if(state.rating >= i+1) Color(0xFFFFC107)
                                else Color.Gray
                            )
                        }
                    }
                }
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = "Mentor to be reviewed",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                AddReviewMentorItem(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    history = state.historySession
                )
            }
        }
    }
}

@Preview
@Composable
private fun AddReviewScreenPreview() {
    AjarinTheme {
        AddReviewScreen(
            state = AddReviewState(
                rating = 1
            ),
            onEvent = {  },
            images = emptyList(),
            addImage = {  },
            removeImage = {  },
            onBackClick = {  }
        )
    }
}