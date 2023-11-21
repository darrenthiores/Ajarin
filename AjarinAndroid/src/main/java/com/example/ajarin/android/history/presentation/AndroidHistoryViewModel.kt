package com.example.ajarin.android.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.ajarin.android.core.domain.use_cases.AndroidGetMentorOrders
import com.example.ajarin.android.core.domain.use_cases.AndroidGetUserOrders
import com.example.ajarin.domain.order.model.Order
import com.example.ajarin.domain.order.model.dummyHistory
import com.example.ajarin.domain.user.use_cases.GetUser
import com.example.ajarin.presentation.history.HistoryEvent
import com.example.ajarin.presentation.history.HistoryViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AndroidHistoryViewModel @Inject constructor(
    private val getUser: GetUser,
    private val getUserOrders: AndroidGetUserOrders,
    private val getMentorOrders: AndroidGetMentorOrders,
): ViewModel() {
    private val _userOrders: MutableStateFlow<PagingData<Order>> = MutableStateFlow(PagingData.empty())
    val userOrders: StateFlow<PagingData<Order>>
        get() = _userOrders.asStateFlow()

    private val _mentorOrders: MutableStateFlow<PagingData<Order>> = MutableStateFlow(PagingData.empty())
    val mentorOrders: StateFlow<PagingData<Order>>
        get() = _mentorOrders.asStateFlow()

    private val viewModel by lazy {
        HistoryViewModel(
            coroutineScope = viewModelScope
        )
    }

    init {
        viewModelScope.launch {
            viewModel.onEvent(
                event = HistoryEvent.OnUpdateIsMentor(
                    isMentor = true
                )
            )

            viewModelScope.launch {
                _mentorOrders.value = PagingData.from(dummyHistory)
            }
        }

        viewModelScope.launch {
            _userOrders.value = PagingData.from(dummyHistory)
        }

//        viewModelScope.launch {
//            val result = getUser()
//            val isMentor = result?.roleType == "2"
//
//            viewModel.onEvent(
//                event = HistoryEvent.OnUpdateIsMentor(
//                    isMentor = isMentor
//                )
//            )
//
//            if (isMentor) {
//                viewModelScope.launch {
//                    getMentorOrders()
//                        .distinctUntilChanged()
//                        .cachedIn(viewModelScope)
//                        .collect {
//                            _mentorOrders.value = it
//                        }
//                }
//            }
//        }
//
//        viewModelScope.launch {
//            getUserOrders()
//                .distinctUntilChanged()
//                .cachedIn(viewModelScope)
//                .collect {
//                    _userOrders.value = it
//                }
//        }
    }

    val state = viewModel.state
}