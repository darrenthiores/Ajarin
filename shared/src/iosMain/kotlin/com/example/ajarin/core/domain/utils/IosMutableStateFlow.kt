package com.example.ajarin.core.domain.utils

import kotlinx.coroutines.flow.MutableStateFlow

class IosMutableStateFlow<T>(
    initialValue: T
): CommonMutableStateFlow<T>(
    MutableStateFlow(initialValue)
)