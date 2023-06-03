package com.example.ajarin.android.di

import android.app.Application
import com.example.ajarin.core.data.local.driver.DatabaseDriverFactory
import com.example.ajarin.database.ChatDatabase
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatDatabaseModule {

    @Provides
    @Singleton
    fun provideDriver(
        app: Application
    ): SqlDriver {
        return DatabaseDriverFactory(app)
            .create()
    }

    @Provides
    @Singleton
    fun provideTranslateDatabase(
        driver: SqlDriver
    ): ChatDatabase {
        return ChatDatabase(driver)
    }
}