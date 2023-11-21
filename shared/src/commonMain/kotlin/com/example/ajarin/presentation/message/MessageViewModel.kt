package com.example.ajarin.presentation.message

import com.example.ajarin.domain.core.utils.toCommonStateFlow
import com.example.ajarin.domain.inbox.use_cases.CreateInbox
import com.example.ajarin.domain.mentor.model.dummyMentors
import com.example.ajarin.domain.mentor.use_cases.GetMentorById
import com.example.ajarin.domain.message.use_cases.GetMessagesById
import com.example.ajarin.domain.message.use_cases.InsertMessage
import com.example.ajarin.domain.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MessageViewModel(
    private val getMessageById: GetMessagesById,
    private val getMentorById: GetMentorById,
    private val createInbox: CreateInbox,
    private val insertMessage: InsertMessage,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(MessageState())
    val state = _state.toCommonStateFlow()

    fun onEvent(event: MessageEvent) {
        when(event) {
            is MessageEvent.OnMessageChange -> {
                if (!state.value.isSendingMessage) {
                    _state.value = state.value.copy(
                        newMessage = event.newText
                    )
                }
            }
            is MessageEvent.SendMessage -> {
                if (state.value.mentor == null) {
                    return
                }

                _state.value = state.value.copy(
                    isSendingMessage = true
                )

                val mentorId = state.value.mentor?.id!!

                viewModelScope.launch {
                    if (state.value.messages.isNotEmpty()) {
                        val inboxId = state.value.messages.first().inboxId

                        insertMessage
                            .execute(
                                inboxId = inboxId,
                                sentToId = mentorId,
                                sentFromId = event.userId,
                                message = state.value.newMessage,
                                mediaUrl = ""
                            )

                        _state.value = state.value.copy(
                            isSendingMessage = false,
                            newMessage = ""
                        )
                    } else {
                        createInbox.execute(
                            sentToId = mentorId,
                            sentFromId = event.userId,
                            message = state.value.newMessage,
                            mediaUrl = ""
                        )

                        _state.value = state.value.copy(
                            isSendingMessage = false,
                            newMessage = ""
                        )
                    }
                }
            }
        }
    }

    fun init(
        mentorId: String,
        userId: String
    ) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                mentorLoading = true
            )

            _state.value = state.value.copy(
                mentor = dummyMentors.firstOrNull { it.id == mentorId },
                mentorError = null,
                mentorLoading = false
            )

            return@launch

            when(
                val result = getMentorById(id = mentorId)
            ) {
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        mentorError = Error(result.message),
                        mentorLoading = false
                    )
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        mentor = result.data,
                        mentorError = null,
                        mentorLoading = false
                    )
                }
            }
        }

        viewModelScope.launch {
            _state.value = state.value.copy(
                messagesLoading = true
            )

            getMessageById
                .execute(
                    sentToId = mentorId,
                    sentFromId = userId
                )
                .onStart {
                    _state.value = state.value.copy(
                        messagesLoading = true
                    )
                }
                .collectLatest {
                    _state.value = state.value.copy(
                        messages = it
                    )
                }
        }
    }
}