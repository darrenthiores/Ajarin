package com.example.ajarin.android.apply_as_mentor.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.ajarin.android.core_ui.theme.AjarinTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ApplyAsMentorEducationDropDown(
    educations: List<String>,
    education: String?,
    isOpen: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onSelectEducation: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var rowSize by remember { mutableStateOf(Size.Zero) }

    Box(
        modifier = modifier
            .onGloballyPositioned { layoutCoordinates ->
                rowSize = layoutCoordinates.size.toSize()
            }
    ) {
        DropdownMenu(
            modifier = Modifier,
            expanded = isOpen,
            onDismissRequest = onDismiss
        ) {
            educations.forEachIndexed { index, education ->
                DropdownMenuItem(
                    onClick = { onSelectEducation(education) },
                    modifier = Modifier
                        .width(
                            with(LocalDensity.current) { rowSize.width.toDp() }
                        )
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = education
                    )
                }

                if (index != educations.lastIndex) {
                    Divider()
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onClick,
            elevation = 5.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = education ?: "Select Last or Current Education"
                )

                Icon(
                    imageVector = if(isOpen) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = if(isOpen) "Close" else "Open"
                )
            }
        }
    }
}

@Preview
@Composable
fun ApplyAsMentorEducationDropDownPreview() {
    AjarinTheme {
        ApplyAsMentorEducationDropDown(
            educations = emptyList(),
            education = null,
            isOpen = false,
            onClick = { },
            onDismiss = {  },
            onSelectEducation = {  }
        )
    }
}