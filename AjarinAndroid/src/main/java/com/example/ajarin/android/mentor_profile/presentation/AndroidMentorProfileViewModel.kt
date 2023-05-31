package com.example.ajarin.android.mentor_profile.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajarin.mentorProfile.domain.use_cases.GetMentorById
import com.example.ajarin.mentorProfile.presentation.MentorProfileViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidMentorProfileViewModel @Inject constructor(
    private val getMentorById: GetMentorById,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val viewModel by lazy {
        MentorProfileViewModel(
            getMentorById = getMentorById,
            coroutineScope = viewModelScope
        )
    }
    val state = viewModel.state

    init {
        val mentorId = savedStateHandle.get<String>("mentor_id")

        mentorId?.let {
            viewModel.initMentor(it)
        }
    }
}