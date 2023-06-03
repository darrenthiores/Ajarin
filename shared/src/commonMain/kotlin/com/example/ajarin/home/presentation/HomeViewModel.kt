package com.example.ajarin.home.presentation

import com.example.ajarin.core.domain.utils.toCommonStateFlow
import com.example.ajarin.home.domain.use_cases.GetUnreadCount
import com.example.ajarin.home.domain.use_cases.SearchMentorByCourse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val searchMentorByCourse: SearchMentorByCourse,
    private val getUnreadCount: GetUnreadCount,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(HomeState())
    val state = _state.toCommonStateFlow()

    init {
        _state.value = state.value.copy(
            mentors = dummyMentors.shuffled()
        )
    }

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.OnSelectCourse -> {
                if (event.course.id == "0") {
                    _state.value = state.value.copy(
                        selectedCourse = event.course,
                        mentors = dummyMentors.shuffled()
                    )

                    return
                }

                _state.value = state.value.copy(
                    selectedCourse = event.course,
                    mentors = searchMentorByCourse.execute(event.course)
                )
            }
        }
    }

    fun initUnreadCount(userId: String) {
        viewModelScope.launch {
            getUnreadCount
                .execute(
                    userId = userId
                )
                .collectLatest {
                    _state.value = state.value.copy(
                        unreadMessageCount = it
                    )
                }
        }
    }
}