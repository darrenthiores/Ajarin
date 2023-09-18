package com.example.ajarin.domain.utils

import platform.Foundation.NSUUID

actual fun randomUUID(): String = NSUUID().UUIDString()