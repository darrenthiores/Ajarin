package com.example.ajarin.core.data.local.driver

import com.example.ajarin.database.ChatDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(
            ChatDatabase.Schema,
            "chat.db"
        )
    }
}