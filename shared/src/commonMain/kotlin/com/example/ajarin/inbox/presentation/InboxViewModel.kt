package com.example.ajarin.inbox.presentation

import com.example.ajarin.core.domain.utils.toCommonStateFlow
import com.example.ajarin.core.utils.Resource
import com.example.ajarin.inbox.domain.use_cases.GetInbox
import com.example.ajarin.inbox.presentation.model.UiMessage
import com.example.ajarin.mentorProfile.domain.use_cases.GetMentorById
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class InboxViewModel(
    private val getInbox: GetInbox,
    private val getMentorById: GetMentorById,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(InboxState())
    val state = _state.toCommonStateFlow()

    fun initInbox(userId: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )

            getInbox
                .execute(
                    userId = userId
                )
                .onStart {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                }
                .collectLatest {
                    _state.value = state.value.copy(
                        inbox = it.map { message ->
                            async {
                                when (
                                    val result = getMentorById.execute(id = message.participantId)
                                ) {
                                    is Resource.Error -> null
                                    is Resource.Loading -> null
                                    is Resource.Success -> {
                                        UiMessage(
                                            inboxId = message.id,
                                            lastMessage = message.lastMessage,
                                            mentor = result.data ?: return@async null
                                        )
                                    }
                                }
                            }
                        }
                        .awaitAll()
                        .filterNotNull()
                    )
                }
        }
    }
}