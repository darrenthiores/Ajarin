package com.example.ajarin.mentorProfile.presentation

import com.example.ajarin.core.domain.utils.toCommonStateFlow
import com.example.ajarin.core.utils.Resource
import com.example.ajarin.mentorProfile.domain.use_cases.GetMentorById
import com.example.ajarin.searchMentor.domain.use_cases.SearchMentor
import com.example.ajarin.searchMentor.presentation.SearchMentorState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow

class MentorProfileViewModel(
    private val getMentorById: GetMentorById,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(MentorProfileState())
    val state = _state.toCommonStateFlow()

    fun initMentor(id: String) {
        _state.value = state.value.copy(
            isFetching = true
        )

        when(
            val result = getMentorById.execute(id)
        ) {
            is Resource.Error -> {
                _state.value = state.value.copy(
                    isError = Error(result.message),
                    isFetching = false
                )
            }
            is Resource.Loading -> Unit
            is Resource.Success -> {
                _state.value = state.value.copy(
                    isError = null,
                    isFetching = false,
                    mentor = result.data
                )
            }
        }
    }
}