package com.example.ajarin.android.bank_account.presentation.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.helper.getImageId
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.bankAccount.presentation.BankAccount
import com.example.ajarin.bankAccount.presentation.dummyAccount
import kotlin.math.roundToInt

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun BankAccountItem(
    modifier: Modifier = Modifier,
    account: BankAccount,
    cardOffset: Float
) {
    var isRevealed by remember {
        mutableStateOf(false)
    }
    val transitionState = remember {
        MutableTransitionState(isRevealed).apply {
            targetState = !isRevealed
        }
    }
    val transition = updateTransition(transitionState, label = "SwipeTransition")
    val offsetTransition by transition.animateFloat(
        label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = 500) },
        targetValueByState = { if (isRevealed) -cardOffset else 0f }
    )

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = "Update"
                )
            }

            IconButton(
                onClick = {  }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colors.error
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset(offsetTransition.roundToInt(), 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        when {
                            dragAmount >= 6 -> {
                                isRevealed = false
                            }
                            dragAmount < -6 -> {
                                isRevealed = true
                            }
                        }
                    }
                }
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
                    painter = painterResource(id = account.bank.getImageId()),
                    contentDescription = "Bank Account Logo",
                    contentScale = ContentScale.Fit
                )

                Column {
                    Text(
                        text = account.name,
                        style = MaterialTheme.typography.subtitle1
                    )

                    Text(
                        text = "${account.bank.bankName} | **** ${account.accountNumber.substring(account.accountNumber.length/2)}"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BankAccountItemPreview() {
    AjarinTheme {
        BankAccountItem(
            account = dummyAccount[0],
            cardOffset = 168f
        )
    }
}