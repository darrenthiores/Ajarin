package com.example.ajarin.core.data.local.database

import com.example.ajarin.database.ChatDatabase
import com.squareup.sqldelight.db.SqlDriver

class DatabaseFactory {
    fun createDatabase(driver: SqlDriver): ChatDatabase {
        return ChatDatabase(driver)
    }
}