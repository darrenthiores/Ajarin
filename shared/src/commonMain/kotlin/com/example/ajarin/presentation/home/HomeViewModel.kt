package com.example.ajarin.presentation.home

import com.example.ajarin.domain.core.utils.toCommonStateFlow
import com.example.ajarin.domain.message.use_cases.GetUnreadCount
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel(
    private val getUnreadCount: GetUnreadCount,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(HomeState())
    val state = _state.toCommonStateFlow()

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.OnSelectCourse -> {
                if (event.course.id == "0") {
                    _state.value = state.value.copy(
                        selectedCourse = event.course
                    )

                    return
                }

                _state.value = state.value.copy(
                    selectedCourse = event.course
                )
            }
        }
    }

    fun initUnreadCount(userId: String) {
//        viewModelScope.launch {
//            getUnreadCount
//                .execute(
//                    userId = userId
//                )
//                .collectLatest {
//                    _state.value = state.value.copy(
//                        unreadMessageCount = it
//                    )
//                }
//        }
    }
}