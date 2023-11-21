package com.example.ajarin.android.mentor_profile.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.ajarin.android.core.domain.use_cases.AndroidGetMentorReviews
import com.example.ajarin.domain.mentor.use_cases.GetMentorById
import com.example.ajarin.domain.review.model.Review
import com.example.ajarin.domain.review.model.dummyReviews
import com.example.ajarin.presentation.mentorProfile.MentorProfileViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AndroidMentorProfileViewModel @Inject constructor(
    private val getMentorById: GetMentorById,
    private val getReviews: AndroidGetMentorReviews,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _reviews: MutableStateFlow<PagingData<Review>> = MutableStateFlow(PagingData.empty())
    val reviews: StateFlow<PagingData<Review>>
        get() = _reviews.asStateFlow()

    private val viewModel by lazy {
        MentorProfileViewModel(
            getMentorById = getMentorById,
            coroutineScope = viewModelScope
        )
    }
    val state = viewModel.state

    init {
        val mentorId = savedStateHandle.get<String>("mentor_id")

//        mentorId?.let {
//            viewModel.initMentor(it)
//
//            viewModelScope.launch {
//                getReviews(
//                    id = mentorId
//                )
//                    .distinctUntilChanged()
//                    .cachedIn(viewModelScope)
//                    .collect { reviews ->
//                        _reviews.value = reviews
//                    }
//            }
//        }

        mentorId?.let {
            viewModel.initMentor(it)
        }

        viewModelScope.launch {
            _reviews.value = PagingData.from(dummyReviews)
        }
    }
}