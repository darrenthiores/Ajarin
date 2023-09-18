package com.example.ajarin.domain.core.utils

import kotlinx.coroutines.flow.MutableStateFlow

class IosMutableStateFlow<T>(
    initialValue: T
): CommonMutableStateFlow<T>(
    MutableStateFlow(initialValue)
)