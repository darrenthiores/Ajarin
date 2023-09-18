package com.example.ajarin.domain.core.utils

import kotlinx.coroutines.flow.StateFlow

expect class CommonStateFlow<T>(
    flow: StateFlow<T>
): StateFlow<T>

fun <T> StateFlow<T>.toCommonStateFlow() = CommonStateFlow(this)