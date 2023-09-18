package com.example.ajarin.android

import android.content.Intent
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
import androidx.navigation.navDeepLink
import com.example.ajarin.android.add_bank.presentation.AddBankScreen
import com.example.ajarin.android.add_bank.presentation.AndroidAddBankViewModel
import com.example.ajarin.android.add_pin.presentation.AddPinScreen
import com.example.ajarin.android.add_pin.presentation.AndroidAddPinViewModel
import com.example.ajarin.android.add_review.presentation.AddReviewScreen
import com.example.ajarin.android.add_review.presentation.AndroidAddReviewViewModel
import com.example.ajarin.android.apply_as_mentor.presentation.AndroidApplyAsMentorViewModel
import com.example.ajarin.android.apply_as_mentor.presentation.ApplyAsMentorScreen
import com.example.ajarin.android.bank_account.presentation.AndroidBankAccountViewModel
import com.example.ajarin.android.bank_account.presentation.BankAccountScreen
import com.example.ajarin.android.booking.presentation.AndroidBookingViewModel
import com.example.ajarin.android.booking.presentation.BookingScreen
import com.example.ajarin.android.booking_success.BookingSuccessScreen
import com.example.ajarin.android.components.AppBottomBar
import com.example.ajarin.android.core_ui.navigation.Route
import com.example.ajarin.android.core_ui.navigation.TopLevelDestination
import com.example.ajarin.android.history.presentation.AndroidHistoryViewModel
import com.example.ajarin.android.history.presentation.HistoryScreen
import com.example.ajarin.android.home.presentation.AndroidHomeViewModel
import com.example.ajarin.android.home.presentation.HomeScreen
import com.example.ajarin.android.inbox.presentation.AndroidInboxViewModel
import com.example.ajarin.android.inbox.presentation.InboxScreen
import com.example.ajarin.android.landing.presentation.login.AndroidLoginViewModel
import com.example.ajarin.android.landing.presentation.login.LoginScreen
import com.example.ajarin.android.landing.presentation.register.AndroidRegisterViewModel
import com.example.ajarin.android.landing.presentation.register.RegisterScreen
import com.example.ajarin.android.landing.presentation.splash.SplashScreen
import com.example.ajarin.android.mentor_profile.presentation.AndroidMentorProfileViewModel
import com.example.ajarin.android.mentor_profile.presentation.MentorProfileScreen
import com.example.ajarin.android.message.presentation.AndroidMessageViewModel
import com.example.ajarin.android.message.presentation.MessageScreen
import com.example.ajarin.android.profile.presentation.AndroidProfileViewModel
import com.example.ajarin.android.profile.presentation.ProfileScreen
import com.example.ajarin.android.search_mentor.presentation.AndroidSearchMentorViewModel
import com.example.ajarin.android.search_mentor.presentation.SearchMentorScreen
import com.example.ajarin.android.session.presentation.AndroidSessionViewModel
import com.example.ajarin.android.session.presentation.SessionScreen
import com.example.ajarin.android.session_as_mentor.presentation.AndroidSessionAsMentorViewModel
import com.example.ajarin.android.session_as_mentor.presentation.SessionAsMentorScreen
import com.example.ajarin.android.verify_pin.presentation.AndroidVerifyPinViewModel
import com.example.ajarin.android.verify_pin.presentation.VerifyPinScreen
import com.example.ajarin.android.withdraw.presentation.AndroidWithdrawViewModel
import com.example.ajarin.android.withdraw.presentation.WithdrawScreen
import com.example.ajarin.android.withdraw_success.WithdrawSuccessScreen
import com.example.ajarin.domain.utils.UiEvent
import timber.log.Timber

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
                    },
                    onMessageClick = {
                        navController.navigate(Route.Inbox.name)
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
                val viewModel: AndroidHistoryViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                HistoryScreen(
                    state = state,
                    onUserClick = { sessionId, mentorId ->
                        navController.navigate(Route.Session.name + "/$sessionId" + "/$mentorId")
                    },
                    onMentorClick = { sessionId, userId ->
                        navController.navigate(Route.SessionAsMentor.name + "/$sessionId" + "/$userId")
                    },
                    onReviewClick = { sessionId ->
                        navController.navigate(Route.AddReview.name + "/$sessionId")
                    },
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(
                route = Route.Session.name + "/{session_id}/{mentor_id}",
                arguments = listOf(
                    navArgument("session_id") {
                        type = NavType.StringType
                    },
                    navArgument("mentor_id") {
                        type = NavType.StringType
                    }
                )
            ) { navBackStackEntry ->
                val sessionId = navBackStackEntry.arguments?.getString("session_id")
                val viewModel: AndroidSessionViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                SessionScreen(
                    state = state,
                    onReviewClick = {
                        sessionId?.let {
                            navController.navigate(Route.AddReview.name + "/$it")
                        }
                    },
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(
                route = Route.SessionAsMentor.name + "/{session_id}/{user_id}",
                arguments = listOf(
                    navArgument("session_id") {
                        type = NavType.StringType
                    },
                    navArgument("user_id") {
                        type = NavType.StringType
                    }
                )
            ) {
                val viewModel: AndroidSessionAsMentorViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                SessionAsMentorScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
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
                    onEvent = viewModel::onEvent,
                    onApplyAsMentorClick = {
                        navController.navigate(Route.ApplyAsMentor.name)
                    },
                    onBankAccountClick = {
                        navController.navigate(Route.BankAccount.name)
                    },
                    onWithdrawClick = {
                        navController.navigate(Route.Withdraw.name)
                    }
                )
            }

            composable(Route.ApplyAsMentor.name) {
                val viewModel: AndroidApplyAsMentorViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                val androidState by viewModel.androidState.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                navController.navigateUp()
                                appState.showSnackBar("Your application is sent!")
                            }
                            is UiEvent.ShowSnackBar -> {
                                appState.showSnackBar(event.message)
                            }
                            else -> Unit
                        }
                    }
                }

                ApplyAsMentorScreen(
                    state = state,
                    androidState = androidState,
                    onEvent = viewModel::onEvent,
                    androidOnEvent = viewModel::onEvent,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(
                route = Route.MentorProfile.name + "/{mentor_id}",
                arguments = listOf(
                    navArgument("mentor_id") {
                        type = NavType.StringType
                    }
                ),
                deepLinks = listOf(
                    navDeepLink {
                        action = Intent.ACTION_VIEW
                        uriPattern = "https://www.ajarin.com/" + Route.MentorProfile.name + "/{mentor_id}"
                    }
                )
            ) { navBackStackEntry ->
                val mentorId = navBackStackEntry.arguments?.getString("mentor_id")
                val viewModel: AndroidMentorProfileViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                MentorProfileScreen(
                    state = state,
                    onBackClick = {
                        navController.navigateUp()
                    },
                    onChatClick = {
                        mentorId?.let {
                            navController.navigate(Route.Message.name + "/$it")
                        }
                    },
                    onBookClick = {
                        mentorId?.let {
                            navController.navigate(Route.Booking.name + "/$it")
                        }
                    }
                )
            }

            composable(
                route = Route.Booking.name + "/{mentor_id}",
                arguments = listOf(
                    navArgument("mentor_id") {
                        type = NavType.StringType
                    }
                )
            ) {
                val viewModel: AndroidBookingViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                navController.navigate(Route.BookingSuccess.name) {
                                    popUpTo(Route.MentorProfile.name + "/{mentor_id}") {
                                        inclusive = true
                                    }
                                }
                            }
                            else -> Unit
                        }
                    }
                }

                BookingScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(Route.BookingSuccess.name) {
                BookingSuccessScreen(
                    onButtonClick = {
                        navController.navigate(TopLevelDestination.History.name) {
                            popUpTo(Route.BookingSuccess.name) {
                                inclusive = true
                            }
                        }
                    },
                    onBackClick = {
                        navController.navigate(TopLevelDestination.Home.name) {
                            popUpTo(Route.BookingSuccess.name) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(Route.Inbox.name) {
                val viewModel: AndroidInboxViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                InboxScreen(
                    state = state,
                    onBackClick = {
                        navController.navigateUp()
                    },
                    onMessageClick = { mentorId ->
                        navController.navigate(Route.Message.name + "/$mentorId")
                    }
                )
            }

            composable(
                route = Route.Message.name + "/{mentor_id}",
                arguments = listOf(
                    navArgument("mentor_id") {
                        type = NavType.StringType
                    }
                )
            ) { navBackStackEntry ->
                val mentorId = navBackStackEntry.arguments?.getString("mentor_id")
                val viewModel: AndroidMessageViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                mentorId?.let {
                    MessageScreen(
                        mentorId = mentorId,
                        state = state,
                        onEvent = viewModel::onEvent,
                        onSendMessage = viewModel::sendMessage,
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }
            }

            composable(
                route = Route.AddReview.name + "/{session_id}",
                arguments = listOf(
                    navArgument("session_id") {
                        type = NavType.StringType
                    }
                )
            ) {
                val viewModel: AndroidAddReviewViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                navController.navigateUp()
                            }
                            else -> Unit
                        }
                    }
                }

                AddReviewScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    images = viewModel.images,
                    addImage = viewModel::addImage,
                    removeImage = viewModel::removeImage,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(Route.BankAccount.name) {
                val viewModel: AndroidBankAccountViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.init()
                    Timber.d("INIT BANK ACCOUNT")
                }

                LaunchedEffect(viewModel.hasPin) {
                    if (!viewModel.hasPin) {
                        navController.navigate(Route.AddPin.name)
                    }
                }

                BankAccountScreen(
                    state = state,
                    onBackClick = {
                        navController.navigateUp()
                    },
                    onAddClick = {
                        navController.navigate(Route.AddBankAccount.name)
                    }
                )
            }

            composable(Route.AddBankAccount.name) {
                val viewModel: AndroidAddBankViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                navController.navigateUp()
                                appState.showSnackBar("Bank Account Successfully Added!")
                            }
                            else -> Unit
                        }
                    }
                }

                AddBankScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(Route.AddPin.name) {
                val viewModel: AndroidAddPinViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                navController.navigateUp()
                            }
                            else -> Unit
                        }
                    }
                }

                AddPinScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onBackClick = {
                        navController.navigate(TopLevelDestination.Profile.name) {
                            popUpTo(TopLevelDestination.Profile.name) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(Route.Withdraw.name) {
                val viewModel: AndroidWithdrawViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                navController.navigate(Route.VerifyPin.name)
                            }
                            else -> Unit
                        }
                    }
                }

                WithdrawScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(Route.WithdrawSuccess.name) {
                WithdrawSuccessScreen(
                    onBackClick = {
                        navController.navigate(TopLevelDestination.Profile.name) {
                            popUpTo(Route.WithdrawSuccess.name) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(Route.VerifyPin.name) {
                val viewModel: AndroidVerifyPinViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                navController.navigate(Route.WithdrawSuccess.name) {
                                    popUpTo(TopLevelDestination.Profile.name) {
                                        inclusive = true
                                    }
                                }
                            }
                            else -> Unit
                        }
                    }
                }

                VerifyPinScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}