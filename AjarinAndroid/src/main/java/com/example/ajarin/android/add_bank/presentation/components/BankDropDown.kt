package com.example.ajarin.android.add_bank.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.ajarin.android.core_ui.helper.getImageId
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.presentation.bankAccount.Bank
import com.example.ajarin.presentation.bankAccount.dummyBanks

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BankDropDown(
    bank: Bank?,
    isOpen: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onSelectBank: (Bank) -> Unit,
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
            dummyBanks.forEachIndexed { index, bank ->
                DropdownMenuItem(
                    onClick = { onSelectBank(bank) },
                    modifier = Modifier
                        .width(
                            with(LocalDensity.current) { rowSize.width.toDp() }
                        )
                ) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = modifier
                                .size(50.dp),
                            painter = painterResource(id = bank.getImageId()),
                            contentDescription = "Bank Account Logo",
                            contentScale = ContentScale.Fit
                        )

                        Text(
                            text = bank.bankName,
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }

                if (index != dummyBanks.lastIndex) {
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
                verticalAlignment = Alignment.CenterVertically
            ) {
                bank?.let {
                    Image(
                        modifier = modifier
                            .size(50.dp),
                        painter = painterResource(id = bank.getImageId()),
                        contentDescription = "Bank Account Logo",
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(32.dp))
                }

                Text(
                    modifier =  Modifier
                        .weight(1f),
                    text = bank?.bankName ?: "Select Bank"
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
fun BookingCourseDropDownPreview() {
    AjarinTheme {
        BankDropDown(
            bank = null,
            isOpen = false,
            onClick = { },
            onDismiss = {  },
            onSelectBank = {  }
        )
    }
}