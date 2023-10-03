package com.example.ajarin.android.apply_as_mentor.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.domain.mentor.use_cases.ApplyAsMentor
import com.example.ajarin.domain.user.use_cases.GetUser
import com.example.ajarin.domain.validation.use_cases.ValidateId
import com.example.ajarin.presentation.applyAsMentor.ApplyAsMentorEvent
import com.example.ajarin.presentation.applyAsMentor.ApplyAsMentorViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AndroidApplyAsMentorViewModel @Inject constructor(
    private val validateId: ValidateId,
    private val getUser: GetUser,
    private val applyAsMentor: ApplyAsMentor
): ViewModel() {
    private val viewModel by lazy {
        ApplyAsMentorViewModel(
            validateId = validateId,
            getUser = getUser,
            applyAsMentor = applyAsMentor,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel.uiEvent

    fun onEvent(event: ApplyAsMentorEvent) {
        viewModel.onEvent(event)
    }

    private val _androidState = MutableStateFlow(AndroidApplyAsMentorState())
    val androidState = _androidState.asStateFlow()

    fun onEvent(event: AndroidApplyAsMentorEvent) {
        when(event) {
            is AndroidApplyAsMentorEvent.OnSelectGradeReportFile -> {
                _androidState.value = androidState.value.copy(
                    gradeReportFile = event.file
                )
            }
            is AndroidApplyAsMentorEvent.OnSelectIdFile -> {
                _androidState.value = androidState.value.copy(
                    idFile = event.file
                )
            }
            is AndroidApplyAsMentorEvent.OnSelectInstitutionIdFile -> {
                _androidState.value = androidState.value.copy(
                    institutionIdFile = event.file
                )
            }
        }
    }
}