package com.example.ajarin.android.search_mentor.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.searchMentor.presentation.SearchMentorEvent
import com.example.ajarin.searchMentor.presentation.SearchMentorState
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun FilterMentorSheet(
    modifier: Modifier = Modifier,
    state: SearchMentorState,
    onEvent: (SearchMentorEvent) -> Unit
) {
    val configuration = LocalConfiguration.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(
                ((configuration.screenHeightDp / 4) * 3).dp
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Filter")
            
            TextButton(
                onClick = {
                    onEvent(
                        SearchMentorEvent.OnReset
                    )
                }
            ) {
                Text(text = "Reset")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Rating",
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            item {
                FilterItem(
                    text = "⭐ 4 keatas",
                    isSelected = state.tempRating != 0
                ) {
                    onEvent(
                        SearchMentorEvent.OnToggleRating
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
            }

            item {
                Text(
                    text = "Course",
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            item {
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    mainAxisSpacing = 8.dp,
                    crossAxisSpacing = 8.dp
                ) {
                    state.courses.forEach { item ->
                        FilterItem(
                            text = item.name,
                            isSelected = state.tempCourse == item
                        ) {
                            onEvent(
                                SearchMentorEvent.OnPickCourse(item)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
            }

            item {
                Text(
                    text = "Education",
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            item {
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    mainAxisSpacing = 8.dp,
                    crossAxisSpacing = 8.dp
                ) {
                    state.educations.forEach { item ->
                        FilterItem(
                            text = item,
                            isSelected = state.tempEducation == item
                        ) {
                            onEvent(
                                SearchMentorEvent.OnPickEducation(item)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
            }

            item {
                Text(
                    text = "Price",
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            item {
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    mainAxisSpacing = 8.dp,
                    crossAxisSpacing = 8.dp
                ) {
                    state.prices.forEach { item ->
                        FilterItem(
                            text = item,
                            isSelected = state.tempPrice == item
                        ) {
                            onEvent(
                                SearchMentorEvent.OnPickPrice(item)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        if (
            state.tempCourse != state.selectedCourse ||
            state.tempRating != state.rating ||
            state.tempPrice != state.price ||
            state.tempEducation != state.education
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onEvent(
                        SearchMentorEvent.OnApply
                    )
                }
            ) {
                Text(
                    text = "Apply",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
private fun FilterMentorSheetPreview() {
    AjarinTheme {
        FilterMentorSheet(
            state = SearchMentorState(),
            onEvent = {  }
        )
    }
}

@Composable
fun FilterItem(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = text,
        style = MaterialTheme.typography.subtitle1.copy(
            color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground
        ),
        modifier = modifier
            .background(
                color = if (isSelected) MaterialTheme.colors.primary.copy(alpha = 0.2f) else MaterialTheme.colors.background,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = if (isSelected) MaterialTheme.colors.primary else Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .padding(10.dp)
    )
}

@Preview
@Composable
private fun FilterItemPreview() {
    AjarinTheme {
        FilterItem(
            text = "SMA 1",
            isSelected = true
        ) {

        }
    }
}

@Composable
fun FilteredItem(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colors.primary.copy(alpha = 0.2f),
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(10.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.primary
            )
        )

        Icon(
            imageVector = Icons.Rounded.Close,
            contentDescription = "Remove",
            modifier = Modifier
                .size(18.dp),
            tint = MaterialTheme.colors.primary
        )
    }
}

@Preview
@Composable
private fun FilteredItemPreview() {
    AjarinTheme {
        FilteredItem(
            text = "SMA 1"
        ) {

        }
    }
}