package com.example.ajarin.domain.core.utils

import kotlinx.coroutines.flow.MutableStateFlow

expect class CommonMutableStateFlow<T>(
    flow: MutableStateFlow<T>
): MutableStateFlow<T>

fun <T> MutableStateFlow<T>.toCommonMutableStateFlow() = CommonMutableStateFlow(this)