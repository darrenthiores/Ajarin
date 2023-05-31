package com.example.ajarin.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ajarin.android.components.AppBottomBar
import com.example.ajarin.core.utils.UiEvent
import com.example.ajarin.android.core_ui.navigation.Route
import com.example.ajarin.android.core_ui.navigation.TopLevelDestination
import com.example.ajarin.android.home.presentation.AndroidHomeViewModel
import com.example.ajarin.android.home.presentation.HomeScreen
import com.example.ajarin.android.landing.presentation.login.AndroidLoginViewModel
import com.example.ajarin.android.landing.presentation.login.LoginScreen
import com.example.ajarin.android.landing.presentation.register.AndroidRegisterViewModel
import com.example.ajarin.android.landing.presentation.register.RegisterScreen
import com.example.ajarin.android.landing.presentation.splash.SplashScreen
import com.example.ajarin.android.mentor_profile.presentation.AndroidMentorProfileViewModel
import com.example.ajarin.android.mentor_profile.presentation.MentorProfileScreen
import com.example.ajarin.android.profile.presentation.AndroidProfileViewModel
import com.example.ajarin.android.profile.presentation.ProfileScreen
import com.example.ajarin.android.search_mentor.presentation.AndroidSearchMentorViewModel
import com.example.ajarin.android.search_mentor.presentation.SearchMentorScreen

@Composable
fun Ajarin(
    appState: AppState = rememberAppState(),
    shouldShowOnBoarding: Boolean = true
) {
    val scaffoldState = appState.scaffoldState
    val navController = appState.navController

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                AppBottomBar(
                    currentDestination = appState.currentDestination,
                    onTabSelected = {
                        navController.navigate(it.name)
                    }
                )
            }
        },
        scaffoldState = scaffoldState
    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .padding(paddingValues),
            navController = navController,
            startDestination = Route.Splash.name
        ) {
            // LANDING
            composable(Route.Splash.name) {
                SplashScreen {
                    if (shouldShowOnBoarding) {
                        navController.navigate(Route.Login.name)
                    } else {
                        navController.navigate(TopLevelDestination.Home.name)
                    }
                }
            }

            composable(Route.Login.name) {
                val viewModel: AndroidLoginViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                navController.navigate(TopLevelDestination.Home.name)
                            }
                            is UiEvent.ShowSnackBar -> appState.showSnackBar(event.message)
                            else -> Unit
                        }
                    }
                }

                LoginScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onSignUp = {
                        navController.navigate(Route.Register.name)
                    }
                )
            }

            composable(Route.Register.name) {
                val viewModel: AndroidRegisterViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                navController.navigate(TopLevelDestination.Home.name)
                            }
                            is UiEvent.ShowSnackBar -> appState.showSnackBar(event.message)
                            else -> Unit
                        }
                    }
                }

                RegisterScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onSignIn = {
                        navController.navigateUp()
                    }
                )
            }

            composable(TopLevelDestination.Home.name) {
                val viewModel: AndroidHomeViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                HomeScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onMentorClick = { mentorId ->
                        navController.navigate(Route.MentorProfile.name + "/$mentorId")
                    }
                )
            }

            composable(TopLevelDestination.Search.name) {
                val viewModel: AndroidSearchMentorViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                SearchMentorScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onMentorClick = { mentorId ->
                        navController.navigate(Route.MentorProfile.name + "/$mentorId")
                    }
                )
            }

            composable(TopLevelDestination.History.name) {

            }

            composable(TopLevelDestination.Profile.name) {
                val viewModel: AndroidProfileViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                navController.navigate(Route.Login.name) {
                                    popUpTo(Route.Splash.name) {
                                        inclusive = true
                                    }
                                }
                            }
                            else -> Unit
                        }
                    }
                }

                ProfileScreen(
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }

            composable(
                route = Route.MentorProfile.name + "/{mentor_id}",
                arguments = listOf(
                    navArgument("mentor_id") {
                        type = NavType.StringType
                    }
                )
            ) {
                val viewModel: AndroidMentorProfileViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                MentorProfileScreen(
                    state = state,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}