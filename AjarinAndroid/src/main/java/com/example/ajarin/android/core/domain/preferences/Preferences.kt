package com.example.ajarin.android.core.domain.preferences

import com.example.ajarin.android.BuildConfig

interface Preferences {
    fun saveShouldShowOnBoarding(shouldShow: Boolean)
    fun loadShouldShowOnBoarding(): Boolean

    companion object {
        const val SHOULD_SHOW_ON_BOARDING = BuildConfig.SHOW_ON_BOARDING_KEY
    }
}