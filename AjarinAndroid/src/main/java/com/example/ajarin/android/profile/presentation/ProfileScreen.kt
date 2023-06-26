package com.example.ajarin.android.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ajarin.android.core_ui.components.NegativeConfirmationDialog
import com.example.ajarin.android.core_ui.components.OutlinedPrimaryButton
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.android.profile.presentation.components.ProfileButton
import com.example.ajarin.android.profile.presentation.components.ProfileWallet
import com.example.ajarin.profile.presentation.ProfileEvent
import com.example.ajarin.profile.presentation.ProfileState

@Composable
fun ProfileScreen(
    state: ProfileState,
    isMentor: Boolean = true,
    onEvent: (ProfileEvent) -> Unit,
    onApplyAsMentorClick: () -> Unit,
    onBankAccountClick: () -> Unit,
    onWithdrawClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colors.primary.copy(
                            alpha = 0.1f
                        )
                    )
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.user?.name?.first()?.uppercase() ?: "?",
                            style = MaterialTheme.typography.h1.copy(
                                fontSize = 32.sp,
                                color = MaterialTheme.colors.onPrimary
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = if (state.isFetchingUser) "loading..." else state.user?.name ?: "null",
                            style = MaterialTheme.typography.h6.copy(
                                color = MaterialTheme.colors.onBackground
                            )
                        )

                        Text(
                            text = if (state.isFetchingUser) "loading..." else state.user?.email ?: "null",
                            style = MaterialTheme.typography.body2.copy(
                                color = MaterialTheme.colors.onBackground.copy(
                                    alpha = 0.5f
                                )
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        if (isMentor) {
            item {
                Spacer(modifier = Modifier.height(16.dp))

                ProfileWallet(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    balance = "0",
                    onWithdrawClick = onWithdrawClick
                )
            }
        }

        if (!isMentor) {
            item {
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedPrimaryButton(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = "Apply As Mentor",
                    textModifier = Modifier
                        .fillMaxWidth()
                ) {
                    onApplyAsMentorClick()
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Personal",
                style = MaterialTheme.typography.subtitle1.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                icon = Icons.Default.AccountCircle,
                text = "Account",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {

            }

            if (isMentor) {
                Spacer(modifier = Modifier.height(8.dp))

                ProfileButton(
                    icon = Icons.Default.AccountCircle,
                    text = "Bank Account",
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    onBankAccountClick()
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "General",
                style = MaterialTheme.typography.subtitle1.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                icon = Icons.Default.Settings,
                text = "Settings",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {

            }

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                icon = Icons.Default.VerifiedUser,
                text = "Security",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {

            }

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                icon = Icons.Default.Note,
                text = "Terms and Conditions",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {

            }

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                icon = Icons.Default.Help,
                text = "Help",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {

            }

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                icon = Icons.Default.Info,
                text = "About",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {

            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            ProfileButton(
                icon = Icons.Default.Logout,
                text = "Log Out",
                color = MaterialTheme.colors.error,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                onEvent(
                    ProfileEvent.ToggleDialog(true)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    if (state.isDialogShow) {
        NegativeConfirmationDialog(
            message = "Are You Sure to Log Out?",
            onDismiss = {
                onEvent(
                    ProfileEvent.ToggleDialog(false)
                )
            },
            onClicked = {
                onEvent(
                    ProfileEvent.LogOut
                )
            },
            cancellationText = "Cancel",
            confirmationText = "Log Out"
        )
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    AjarinTheme {
        ProfileScreen(
            state = ProfileState(),
            onEvent = {  },
            onApplyAsMentorClick = {  },
            onBankAccountClick = {  }
        ) {
            
        }
    }
}