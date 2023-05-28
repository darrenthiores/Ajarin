package com.example.ajarin.android.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.ajarin.android.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    @Provides
    @Singleton
    @Named("sharedPreferences")
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences =
        context.getSharedPreferences(
            BuildConfig.PREF_NAME,
            MODE_PRIVATE
        )

}