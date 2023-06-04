package com.example.ajarin.android.add_review.presentation.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ajarin.android.R
import com.example.ajarin.android.core_ui.theme.AjarinTheme

@Composable
fun AddReviewImages(
    modifier: Modifier = Modifier,
    images: List<Uri>,
    onRemoveClick: (Uri) -> Unit,
    onAddClick: () -> Unit
) {
    val context = LocalContext.current

    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsIndexed(
            items = images,
            key = { index, uri -> "$uri-$index" }
        ) { _, uri ->
            Box(
                modifier = Modifier
                    .size(200.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    model = ImageRequest
                        .Builder(context)
                        .data(uri)
                        .build(),
                    contentDescription = "Review Image",
                    placeholder = painterResource(id = R.drawable.ic_no_picture),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .offset(x = 8.dp, y = (-8).dp)
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(Color.Red)
                        .align(Alignment.TopEnd)
                        .clickable {
                            onRemoveClick(uri)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Remove Image",
                        tint = MaterialTheme.colors.background
                    )
                }
            }
        }

        if (images.size < 5) {
            item {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(
                            color = Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            onAddClick()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Add Image"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AddReviewImagesPreview() {
    AjarinTheme {
        AddReviewImages(
            images = listOf(
                Uri.EMPTY
            ),
            onRemoveClick = {  },
            onAddClick = {  }
        )
    }
}