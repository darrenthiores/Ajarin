package com.example.ajarin.presentation.applyAsMentor

import com.example.ajarin.domain.core.utils.toCommonFlow
import com.example.ajarin.domain.core.utils.toCommonStateFlow
import com.example.ajarin.domain.mentor.use_cases.ApplyAsMentor
import com.example.ajarin.domain.user.use_cases.GetUser
import com.example.ajarin.domain.utils.Resource
import com.example.ajarin.domain.utils.UiEvent
import com.example.ajarin.domain.validation.use_cases.ValidateId
import com.example.ajarin.domain.validation.utils.ValidationError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ApplyAsMentorViewModel (
    private val validateId: ValidateId,
    private val getUser: GetUser,
    private val applyAsMentor: ApplyAsMentor,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(ApplyAsMentorState())
    val state = _state.toCommonStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent
        .receiveAsFlow()
        .toCommonFlow()

    fun onEvent(event: ApplyAsMentorEvent) {
        when(event) {
            ApplyAsMentorEvent.OnApply -> {
                viewModelScope.launch {
                    val stateValue = state.value
                    val user = getUser()
                    val result = applyAsMentor(
                        id = user?.id ?: return@launch,
                        education = stateValue.lastEducation ?: return@launch,
                        courses = stateValue.selectedCourses.map { it.id },
                        price = stateValue.proposedFee
                    )

                    when (result) {
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                applyError = Error(result.message)
                            )
                        }
                        is Resource.Loading -> Unit
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                applySuccess = true
                            )

                            viewModelScope.launch {
                                _uiEvent.send(
                                    UiEvent.Success
                                )
                            }
                        }
                    }
                }
            }
            is ApplyAsMentorEvent.OnFeeChange -> {
                _state.value = state.value.copy(
                    proposedFee = event.newText
                )
            }
            is ApplyAsMentorEvent.OnFullNameChange -> {
                _state.value = state.value.copy(
                    fullName = event.newText
                )
            }
            is ApplyAsMentorEvent.OnIdChange -> {
                if (event.newText.length > 16) {
                    return
                }

                _state.value = state.value.copy(
                    id = event.newText
                )

                val isValid = validateId.invoke(id = event.newText)

                if (isValid.isSuccess) {
                    _state.value = state.value.copy(
                        idError = null
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        idError = isValid.exceptionOrNull() as? ValidationError
                    )
                }
            }
            is ApplyAsMentorEvent.OnInstitutionChange -> {
                _state.value = state.value.copy(
                    institutionName = event.newText
                )
            }
            is ApplyAsMentorEvent.OnPickEducation -> {
                _state.value = state.value.copy(
                    lastEducation = event.education
                )
            }
            is ApplyAsMentorEvent.ToggleEducationDropDown -> {
                _state.value = state.value.copy(
                    isEducationDropDownOpen = event.isOpen
                )
            }
            is ApplyAsMentorEvent.OnPickCourse -> {
                val newList = state.value.selectedCourses
                    .toMutableList()

                if (newList.contains(event.course)) {
                    newList.remove(event.course)
                }
                else {
                    newList.add(event.course)
                }

                if (newList.size > 3) {
                    _state.value = state.value.copy(
                        coursesError = ValidationError("You can only pick no more than 3 courses")
                    )

                    viewModelScope.launch {
                        _uiEvent.send(
                            UiEvent.ShowSnackBar("You can only pick no more than 3 courses")
                        )
                    }

                    return
                }

                _state.value = state.value.copy(
                    selectedCourses = newList.toList(),
                    coursesError = null
                )
            }
            is ApplyAsMentorEvent.OnPickSchedule -> {
                val newList = state.value.selectedSchedule
                    .toMutableList()

                if (newList.contains(event.schedule)) {
                    newList.remove(event.schedule)
                }
                else {
                    newList.add(event.schedule)
                }

                _state.value = state.value.copy(
                    selectedSchedule = newList.toList()
                )
            }
        }
    }
}