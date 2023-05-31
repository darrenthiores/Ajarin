package com.example.ajarin.android.booking.presentation

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.booking.presentation.components.BookingBottomBar
import com.example.ajarin.android.booking.presentation.components.BookingCourseDropDown
import com.example.ajarin.android.booking.presentation.components.BookingMentorCard
import com.example.ajarin.android.booking.presentation.components.BookingPaymentMethodField
import com.example.ajarin.android.booking.presentation.sheets.BookingPaymentMethodSheet
import com.example.ajarin.android.booking.presentation.sheets.BookingScheduleSheet
import com.example.ajarin.android.core_ui.components.ClickableField
import com.example.ajarin.android.core_ui.components.CommonHeader
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.booking.presentation.BookingEvent
import com.example.ajarin.booking.presentation.BookingState
import kotlinx.coroutines.launch
import kotlinx.datetime.toLocalDate
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookingScreen(
    state: BookingState,
    onEvent: (BookingEvent) -> Unit,
    onBackClick: () -> Unit
) {
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    var sheetContent by remember {
        mutableStateOf<BookingSheetContent?>(null)
    }

    val dCalendar = Calendar.getInstance()
    val dYear = dCalendar.get(Calendar.YEAR)
    val dMonth = dCalendar.get(Calendar.MONTH)
    val dDay = dCalendar.get(Calendar.DAY_OF_MONTH)

    dCalendar.time = Date()

    val pickDatePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            val dateString = if(mDayOfMonth <= 9) "0$mDayOfMonth" else "$mDayOfMonth"
            val month = mMonth + 1
            val monthString = if(month <= 9) "0$month" else "$month"

            onEvent(
                BookingEvent.PickDate(
                    date = "$mYear-$monthString-$dateString".toLocalDate()
                )
            )
        },
        dYear,
        dMonth,
        dDay
    )

    BottomSheetScaffold(
        topBar = {
            CommonHeader(
                title = "Booking Session",
                onBackClick = onBackClick
            )
        },
        sheetContent = {
            if (sheetContent == BookingSheetContent.Schedule) {
                BookingScheduleSheet(
                    pickedDate = state.date,
                    onClick = {
                        onEvent(
                            BookingEvent.PickSchedule(it)
                        )

                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.collapse()
                        }
                    },
                    onBackClick = {
                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.collapse()
                        }
                    }
                )
            }

            if (sheetContent == BookingSheetContent.Payment) {
                BookingPaymentMethodSheet(
                    onClick = {
                        onEvent(
                            BookingEvent.PickPaymentMethod(it)
                        )

                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.collapse()
                        }
                    },
                    onBackClick = {
                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.collapse()
                        }
                    }
                )
            }
        },
        sheetPeekHeight = 0.dp,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
        scaffoldState = scaffoldState
    ) {
        Scaffold(
            bottomBar = {
                BookingBottomBar(
                    total = state.mentor?.price ?: "0",
                    onPay = {
                        onEvent(
                            BookingEvent.Book
                        )
                    }
                )
            }
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
                        text = "Mentor Detail",
                        style = MaterialTheme.typography.subtitle1
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    BookingMentorCard(
                        mentor = state.mentor
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    BookingCourseDropDown(
                        courses = state.mentor?.courses ?: emptyList(),
                        course = state.course,
                        isOpen = state.isCourseDropDownOpen,
                        onClick = {
                            onEvent(
                                BookingEvent.ToggleCourseDropDown(!state.isCourseDropDownOpen)
                            )
                        },
                        onDismiss = {
                            onEvent(
                                BookingEvent.ToggleCourseDropDown(false)
                            )
                        },
                        onSelectCourse = {
                            onEvent(
                                BookingEvent.PickCourse(it)
                            )

                            onEvent(
                                BookingEvent.ToggleCourseDropDown(false)
                            )
                        }
                    )
                }

                item {
                    Text(
                        text = "Schedule",
                        style = MaterialTheme.typography.subtitle1
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ClickableField(
                        title = "Pick Date",
                        value = if (state.date != null) state.date.toString() else null,
                        onClick = {
                            pickDatePickerDialog.datePicker.minDate = System.currentTimeMillis()
                            pickDatePickerDialog.show()
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ClickableField(
                        title = "Pick Schedule",
                        value = state.schedule?.let { schedule -> "Session ${schedule.id} - ${schedule.time}" },
                        onClick = {
                            sheetContent = BookingSheetContent.Schedule

                            coroutineScope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        }
                    )
                }

                item {
                    Text(
                        text = "Payment Method",
                        style = MaterialTheme.typography.subtitle1
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    BookingPaymentMethodField(
                        title = "Pick Payment Method",
                        value = state.paymentMethod,
                        onClick = {
                            sheetContent = BookingSheetContent.Payment

                            coroutineScope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        }
                    )
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
                            text = "Rp. ${state.mentor?.price}",
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun BookingScreenPreview() {
    AjarinTheme {
        BookingScreen(
            state = BookingState(),
            onEvent = {  },
            onBackClick = {  }
        )
    }
}

private enum class BookingSheetContent {
    Schedule,
    Payment
}