package com.example.ajarin.core.utils

import platform.Foundation.NSUUID

actual fun randomUUID(): String = NSUUID().UUIDString()