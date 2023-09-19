package com.example.ajarin.presentation.mentorProfile

import com.example.ajarin.domain.core.utils.toCommonStateFlow
import com.example.ajarin.domain.mentor.use_cases.GetMentorById
import com.example.ajarin.domain.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MentorProfileViewModel(
    private val getMentorById: GetMentorById,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(MentorProfileState())
    val state = _state.toCommonStateFlow()

    fun initMentor(id: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isFetching = true
            )

            when(
                val result = getMentorById(id)
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
}