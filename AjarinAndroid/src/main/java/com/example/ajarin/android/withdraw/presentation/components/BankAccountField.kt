package com.example.ajarin.android.withdraw.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.helper.getImageId
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.presentation.bankAccount.BankAccount
import com.example.ajarin.presentation.bankAccount.dummyAccount

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BankAccountField(
    modifier: Modifier = Modifier,
    account: BankAccount?,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            account?.let {
                Image(
                    modifier = modifier
                        .size(50.dp),
                    painter = painterResource(id = account.bank.getImageId()),
                    contentDescription = "Bank Account Logo",
                    contentScale = ContentScale.Fit
                )
            }

            if (account != null) {
                Column {
                    Text(
                        text = account.name,
                        style = MaterialTheme.typography.subtitle1
                    )

                    Text(
                        text = "${account.bank.bankName} | **** ${account.accountNumber.substring(account.accountNumber.length/2)}"
                    )
                }
            } else {
                Text(
                    text = "Select Bank Account",
                    style = MaterialTheme.typography.subtitle1
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Preview
@Composable
private fun BankAccountFieldPreview() {
    AjarinTheme {
        BankAccountField(
            account = dummyAccount[0]
        ) {

        }
    }
}