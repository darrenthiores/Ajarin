package com.example.ajarin.android.di.preferences

import com.example.ajarin.android.core.data.preferences.EncTokenPreferences
import com.example.ajarin.domain.core.preferences.TokenPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class EncTokenPreferenceModule {

    @Binds
    abstract fun providePreferences(
        preferences: EncTokenPreferences
    ): TokenPreferences
}