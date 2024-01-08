package com.example.ajarin.android.core_ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ajarin.android.R
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.domain.core.model.Course
import com.example.ajarin.domain.mentor.model.Mentor
import com.google.accompanist.flowlayout.FlowRow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FixedMentorCard(
    modifier: Modifier = Modifier,
    mentor: Mentor,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp / 2
    val cardWidth = (screenWidth - 24 - 8)

    Card(
        modifier = modifier
            .width(
                cardWidth.dp
            ),
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp),
        onClick = onClick
    ) {
        Column {
            AsyncImage(
                modifier = modifier
                    .fillMaxWidth()
                    .aspectRatio(4/5f),
                contentScale = ContentScale.Crop,
                model = ImageRequest
                    .Builder(context)
                    .data(mentor.photoUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.boy),
                error = painterResource(id = R.drawable.boy),
                contentDescription = mentor.id + " photo"
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = mentor.name,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Text(
                    text = mentor.education,
                    style = MaterialTheme.typography.body2.copy(
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    crossAxisSpacing = 4.dp,
                    mainAxisSpacing = 4.dp
                ) {
                    InformationItem(
                        information = mentor.rating,
                        icon = Icons.Default.ThumbUp
                    )

                    mentor.courses.forEachIndexed { index, course ->
                        if (index < 2) {
                            InformationItem(
                                information = course.name,
                                icon = Icons.Default.Book
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Rp. ${mentor.price}",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun MentorCardPreview() {
    AjarinTheme {
        FixedMentorCard(
            mentor = Mentor(
                id = "0",
                photoUrl = "https://fulaby-feed-thumbnail.oss-ap-southeast-5.aliyuncs.com/05aa10b1-7786-4c6f-8cb1-1db90a74539d-fulabyFeedThumbnail.jpeg",
                name = "Jonni",
                education = "SMA 2",
                rating = "4.5",
                courses = listOf(
                    Course(
                        id = "0",
                        name = "Matematika"
                    )
                ),
                price = "15.000",
                priceCategory = "<50k"
            )
        ) {
            
        }
    }
}

@Composable
private fun InformationItem(
    modifier: Modifier = Modifier,
    information: String,
    icon: ImageVector
) {
    Row(
        modifier = modifier
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(12.dp),
            imageVector = icon,
            contentDescription = information,
            tint = Color.DarkGray
        )

        Text(
            text = information,
            style = MaterialTheme.typography.caption.copy(
                color = Color.DarkGray,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Preview
@Composable
private fun InformationItemPreview() {
    AjarinTheme {
        InformationItem(
            information = "4.3",
            icon = Icons.Default.ThumbUp
        )
    }
}