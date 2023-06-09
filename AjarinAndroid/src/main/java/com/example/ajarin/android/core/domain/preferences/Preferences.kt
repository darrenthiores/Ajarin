package com.example.ajarin.android.core.domain.preferences

import com.example.ajarin.android.BuildConfig

interface Preferences {
    fun saveShouldShowOnBoarding(shouldShow: Boolean)
    fun loadShouldShowOnBoarding(): Boolean

    fun saveHasPin(hasPin: Boolean)

    fun loadHasPin(): Boolean

    companion object {
        const val SHOULD_SHOW_ON_BOARDING = BuildConfig.SHOW_ON_BOARDING_KEY
        const val HAS_PIN = BuildConfig.HAS_PIN_KEY
    }
}