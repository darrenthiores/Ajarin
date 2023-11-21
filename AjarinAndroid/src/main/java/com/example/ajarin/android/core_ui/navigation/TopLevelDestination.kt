package com.example.ajarin.android.core_ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestination(
    val icon: ImageVector
) {
    Home(
        icon = Icons.Default.Home
    ),
    Search(
        icon = Icons.Default.Search
    ),
    History(
        icon = Icons.Default.History
    ),
    Profile(
        icon = Icons.Default.Person
    );

    companion object{
        fun fromRoute(route: String?): TopLevelDestination? =
            when (route?.substringBefore("/")) {
                Home.name -> Home
                Search.name -> Search
                History.name -> History
                Profile.name -> Profile
                else -> {
                    when (route?.substringBefore("?")) {
                        Home.name -> Home
                        Search.name -> Search
                        History.name -> History
                        Profile.name -> Profile
                        else -> null
                    }
                }
            }
    }
}