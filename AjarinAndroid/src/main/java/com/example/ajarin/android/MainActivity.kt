package com.example.ajarin.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.ajarin.android.core.domain.preferences.Preferences
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AjarinTheme {
                Ajarin(
                    shouldShowOnBoarding = preferences.loadShouldShowOnBoarding()
                )
            }
        }
    }
}