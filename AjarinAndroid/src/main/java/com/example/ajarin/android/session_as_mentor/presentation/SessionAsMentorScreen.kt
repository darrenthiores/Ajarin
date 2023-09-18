package com.example.ajarin.android.session_as_mentor.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.core_ui.components.BasicTextField
import com.example.ajarin.android.core_ui.components.CommonHeader
import com.example.ajarin.android.core_ui.components.PrimaryButton
import com.example.ajarin.android.session.presentation.components.SessionPaymentMethodItem
import com.example.ajarin.android.session_as_mentor.presentation.components.SessionUserCard
import com.example.ajarin.presentation.sessionAsMentor.SessionAsMentorEvent
import com.example.ajarin.presentation.sessionAsMentor.SessionAsMentorState

@Composable
fun SessionAsMentorScreen(
    state: SessionAsMentorState,
    onEvent: (SessionAsMentorEvent) -> Unit,
    onBackClick: () -> Unit
) {
    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            CommonHeader(
                title = "Session",
                onBackClick = onBackClick
            )
        },
        floatingActionButton = {
            if (state.historySession?.status == "1") {
                PrimaryButton(
                    text = "Start"
                ) {
                    onEvent(
                        SessionAsMentorEvent.OnStartClass
                    )
                }
            }

            if (state.historySession?.status == "2") {
                PrimaryButton(
                    text = "Finish"
                ) {
                    onEvent(
                        SessionAsMentorEvent.OnFinishClass
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Text(
                    text = "User Detail",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                SessionUserCard(
                    user = state.user,
                    course = state.historySession?.course
                )
            }

            item {
                Text(
                    text = "Schedule",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "${state.historySession?.date}, ${state.historySession?.schedule?.time}",
                    style = MaterialTheme.typography.subtitle1
                )
            }

            item {
                Divider()
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Links",
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    TextButton(
                        onClick = {
                            onEvent(
                                SessionAsMentorEvent.OnToggleEditSessionInfo
                            )
                        }
                    ) {
                        Text(text = "Edit")
                    }

                    if (
                        state.sessionInfo?.mainLink != state.mainLinkText ||
                        state.sessionInfo?.backupLink != state.backupLinkText ||
                        state.sessionInfo?.materialLink != state.materialLinkText
                    ) {
                        TextButton(
                            onClick = {
                                onEvent(
                                    SessionAsMentorEvent.OnSaveEdit
                                )
                            }
                        ) {
                            Text(text = "Save")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Main Link",
                    style = MaterialTheme.typography.subtitle1
                )

                if (state.isSessionInfoEditable) {
                    BasicTextField(
                        label = "Main Link",
                        text = state.mainLinkText,
                        onTextChange = {
                            onEvent(
                                SessionAsMentorEvent.OnMainLinkTextChange(it)
                            )
                        },
                        error = null
                    )
                } else {
                    Text(
                        modifier = Modifier
                            .clickable {
                                state.sessionInfo?.mainLink?.let { link ->
                                    uriHandler.openUri(link)
                                }
                            },
                        text = state.sessionInfo?.mainLink ?: "loading...",
                        style = MaterialTheme.typography.caption.copy(
                            color = Color.Gray
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Backup Link",
                    style = MaterialTheme.typography.subtitle1
                )

                if (state.isSessionInfoEditable) {
                    BasicTextField(
                        label = "Backup Link",
                        text = state.backupLinkText,
                        onTextChange = {
                            onEvent(
                                SessionAsMentorEvent.OnBackupLinkTextChange(it)
                            )
                        },
                        error = null
                    )
                } else {
                    Text(
                        modifier = Modifier
                            .clickable {
                                state.sessionInfo?.backupLink?.let { link ->
                                    uriHandler.openUri(link)
                                }
                            },
                        text = state.sessionInfo?.backupLink ?: "loading...",
                        style = MaterialTheme.typography.caption.copy(
                            color = Color.Gray
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Material Link",
                    style = MaterialTheme.typography.subtitle1
                )

                if (state.isSessionInfoEditable) {
                    BasicTextField(
                        label = "Material Link",
                        text = state.materialLinkText,
                        onTextChange = {
                            onEvent(
                                SessionAsMentorEvent.OnMaterialLinkTextChange(it)
                            )
                        },
                        error = null
                    )
                } else {
                    Text(
                        modifier = Modifier
                            .clickable {
                                state.sessionInfo?.materialLink?.let { link ->
                                    uriHandler.openUri(link)
                                }
                            },
                        text =state.sessionInfo?.materialLink ?: "loading...",
                        style = MaterialTheme.typography.caption.copy(
                            color = Color.Gray
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Text(
                    text = "Payment Method",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                SessionPaymentMethodItem(method = state.historySession?.paymentMethod)
            }

            item {
                Divider()
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Platform Fee",
                        style = MaterialTheme.typography.subtitle1
                    )

                    Text(
                        text = "Rp. 0",
                        style = MaterialTheme.typography.subtitle1
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Discount",
                        style = MaterialTheme.typography.subtitle1
                    )

                    Text(
                        text = "- Rp. 0",
                        style = MaterialTheme.typography.subtitle1
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Mentor Fee",
                        style = MaterialTheme.typography.subtitle1
                    )

                    Text(
                        text = "Rp. ${state.historySession?.mentorPrice}",
                        style = MaterialTheme.typography.subtitle1
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Divider()

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total Price",
                        style = MaterialTheme.typography.subtitle1
                    )

                    Text(
                        text = "Rp. ${state.historySession?.totalPrice}",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
            
            if (state.historySession?.status == "1" || state.historySession?.status == "2") {
                item {  
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}