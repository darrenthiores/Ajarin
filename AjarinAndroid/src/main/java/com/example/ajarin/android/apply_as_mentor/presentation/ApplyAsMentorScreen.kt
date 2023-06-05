package com.example.ajarin.android.apply_as_mentor.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.apply_as_mentor.presentation.components.ApplyAsMentorEducationCourseDropDown
import com.example.ajarin.android.apply_as_mentor.presentation.helper.getName
import com.example.ajarin.android.apply_as_mentor.presentation.sheets.ApplyAsMentorCourseSheet
import com.example.ajarin.android.apply_as_mentor.presentation.sheets.ApplyAsMentorScheduleSheet
import com.example.ajarin.android.core_ui.components.BasicTextField
import com.example.ajarin.android.core_ui.components.ClickableField
import com.example.ajarin.android.core_ui.components.CommonHeader
import com.example.ajarin.android.core_ui.components.PrimaryButton
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import com.example.ajarin.applyAsMentor.presentation.ApplyAsMentorEvent
import com.example.ajarin.applyAsMentor.presentation.ApplyAsMentorState
import com.example.ajarin.searchMentor.presentation.dummyEducations
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ApplyAsMentorScreen(
    state: ApplyAsMentorState,
    androidState: AndroidApplyAsMentorState,
    onEvent: (ApplyAsMentorEvent) -> Unit,
    androidOnEvent: (AndroidApplyAsMentorEvent) -> Unit,
    onBackClick: () -> Unit
) {
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    var sheetContent by remember {
        mutableStateOf<ApplyAsMentorSheet?>(null)
    }

    val idLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { pdfUri ->
        pdfUri?.let {
            androidOnEvent(
                AndroidApplyAsMentorEvent.OnSelectIdFile(it)
            )
        }
    }

    val gradeReportLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { pdfUri ->
        pdfUri?.let {
            androidOnEvent(
                AndroidApplyAsMentorEvent.OnSelectGradeReportFile(it)
            )
        }
    }

    val institutionIdLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { pdfUri ->
        pdfUri?.let {
            androidOnEvent(
                AndroidApplyAsMentorEvent.OnSelectInstitutionIdFile(it)
            )
        }
    }

    BottomSheetScaffold(
        topBar = {
            CommonHeader(
                title = "Apply As Mentor",
                onBackClick = onBackClick
            )
        },
        floatingActionButton = {
            Layout(
                content = {
                    PrimaryButton(
                        text = "Apply",
                        isEnabled = state.fullName.isNotBlank() &&
                                state.id.isNotBlank() &&
                                state.idError == null &&
                                state.lastEducation != null &&
                                state.selectedCourses.isNotEmpty() &&
                                androidState.idFile != null &&
                                androidState.gradeReportFile != null
                    ) {
                        onEvent(
                            ApplyAsMentorEvent.OnApply
                        )
                    }
                }
            ) { measurable, constraints ->
                val placeable = measurable.first().measure(constraints)

                layout(placeable.width, placeable.height) {
                    placeable.placeRelative(0, -placeable.height)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        sheetContent = {
            if (sheetContent == ApplyAsMentorSheet.SelectCourses) {
                ApplyAsMentorCourseSheet(
                    selectedCourses = state.selectedCourses,
                    onClick = {
                        onEvent(
                            ApplyAsMentorEvent.OnPickCourse(it)
                        )
                    },
                    onBackClick = {
                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.collapse()
                        }
                    }
                )
            }

            if (sheetContent == ApplyAsMentorSheet.SelectSchedules) {
                ApplyAsMentorScheduleSheet(
                    selectedSchedules = state.selectedSchedule,
                    onClick = {
                        onEvent(
                            ApplyAsMentorEvent.OnPickSchedule(it)
                        )
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
                    text = "Personal",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                BasicTextField(
                    label = "Full Name",
                    text = state.fullName,
                    onTextChange = {
                        onEvent(
                            ApplyAsMentorEvent.OnFullNameChange(it)
                        )
                    },
                    error = state.fullNameError
                )

                Spacer(modifier = Modifier.height(8.dp))

                BasicTextField(
                    label = "Id",
                    text = state.id,
                    onTextChange = {
                        onEvent(
                            ApplyAsMentorEvent.OnIdChange(it)
                        )
                    },
                    error = state.idError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                ClickableField(
                    title = "Id File (as pdf)",
                    value = androidState.idFile?.getName(context),
                    onClick = {
                        idLauncher.launch("application/pdf")
                    }
                )
            }

            item {
                Text(
                    text = "Education",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                ApplyAsMentorEducationCourseDropDown(
                    educations = dummyEducations,
                    education = state.lastEducation,
                    isOpen = state.isEducationDropDownOpen,
                    onClick = {
                        onEvent(
                            ApplyAsMentorEvent.ToggleEducationDropDown(!state.isEducationDropDownOpen)
                        )
                    },
                    onDismiss = {
                        onEvent(
                            ApplyAsMentorEvent.ToggleEducationDropDown(false)
                        )
                    },
                    onSelectEducation = {
                        onEvent(
                            ApplyAsMentorEvent.OnPickEducation(it)
                        )

                        onEvent(
                            ApplyAsMentorEvent.ToggleEducationDropDown(false)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                BasicTextField(
                    label = "Institution (optional)",
                    text = state.institutionName,
                    onTextChange = {
                        onEvent(
                            ApplyAsMentorEvent.OnInstitutionChange(it)
                        )
                    },
                    error = state.institutionError
                )

                Spacer(modifier = Modifier.height(8.dp))

                ClickableField(
                    title = "Institution Id File (as pdf) (optional)",
                    value = androidState.institutionIdFile?.getName(context),
                    onClick = {
                        institutionIdLauncher.launch("application/pdf")
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                ClickableField(
                    title = "Select Courses",
                    value = if (state.selectedCourses.isNotEmpty()) "Selected ${state.selectedCourses.size} Course(s)" else null,
                    onClick = {
                        sheetContent = ApplyAsMentorSheet.SelectCourses

                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                ClickableField(
                    title = "Grade Report File (as pdf)",
                    value = androidState.gradeReportFile?.getName(context),
                    onClick = {
                        gradeReportLauncher.launch("application/pdf")
                    }
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

                ClickableField(
                    title = "Select Schedules",
                    value = if (state.selectedSchedule.isNotEmpty()) "Selected ${state.selectedSchedule.size} Session(s)" else null,
                    onClick = {
                        sheetContent = ApplyAsMentorSheet.SelectSchedules

                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    }
                )
            }

            item {
                Text(
                    text = "Fee",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                BasicTextField(
                    label = "Proposed Fee (optional)",
                    text = state.proposedFee,
                    onTextChange = {
                        onEvent(
                            ApplyAsMentorEvent.OnFeeChange(it)
                        )
                    },
                    error = state.proposedFeeError
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Preview
@Composable
private fun ApplyAsMentorScreenPreview() {
    AjarinTheme {
        ApplyAsMentorScreen(
            state = ApplyAsMentorState(),
            androidState = AndroidApplyAsMentorState(),
            onEvent = {  },
            androidOnEvent = {  },
            onBackClick = {  }
        )
    }
}

private enum class ApplyAsMentorSheet {
    SelectCourses,
    SelectSchedules
}