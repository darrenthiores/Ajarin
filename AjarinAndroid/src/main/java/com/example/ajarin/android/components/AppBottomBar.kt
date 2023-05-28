package com.example.ajarin.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.example.ajarin.android.core_ui.navigation.TopLevelDestination
import com.example.ajarin.android.core_ui.theme.AjarinTheme

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    onTabSelected: (TopLevelDestination) -> Unit,
) {
    val currentTopLevelDestination = TopLevelDestination.fromRoute(
        currentDestination?.route
    )

    BottomAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        ) {
            TopLevelDestination.values().forEach { destination ->
                BottomNavigationItem(
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (currentTopLevelDestination == destination) {
                                Spacer(modifier = Modifier.weight(1f))
                            }

                            Icon(
                                imageVector = destination.icon,
                                contentDescription = destination.name + " icon",
                                modifier = Modifier
                                    .size(24.dp)
                            )

                            Text(
                                text = destination.name,
                                style = MaterialTheme.typography.caption
                            )

                            if (currentTopLevelDestination == destination) {
                                Spacer(modifier = Modifier.weight(1f))

                                Box(
                                    modifier = Modifier
                                        .width(50.dp)
                                        .height(2.dp)
                                        .background(
                                            MaterialTheme.colors.onPrimary,
                                            RoundedCornerShape(
                                                topStart = 8.dp,
                                                topEnd = 8.dp
                                            )
                                        )
                                )

                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    },
                    selected = currentTopLevelDestination == destination,
                    onClick = {
                        onTabSelected(destination)
                    },
                    selectedContentColor = MaterialTheme.colors.onPrimary,
                    unselectedContentColor = Color.LightGray
                )
            }
        }
    }
}

@Preview
@Composable
fun AppBottomBarPreview() {
    AjarinTheme {
        AppBottomBar(
            currentDestination = null,
            onTabSelected = {  }
        )
    }
}