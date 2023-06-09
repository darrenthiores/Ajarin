package com.example.ajarin.android.core.data.preferences

import android.content.SharedPreferences
import com.example.ajarin.android.core.domain.preferences.Preferences
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class DefaultPreferences @Inject constructor(
    @Named("sharedPreferences") private val sharedPref: SharedPreferences
): Preferences {
    override fun saveShouldShowOnBoarding(shouldShow: Boolean) {
        sharedPref
            .edit()
            .putBoolean(Preferences.SHOULD_SHOW_ON_BOARDING, shouldShow)
            .apply()
    }

    override fun loadShouldShowOnBoarding(): Boolean =
        sharedPref.getBoolean(Preferences.SHOULD_SHOW_ON_BOARDING, true)

    override fun saveHasPin(hasPin: Boolean) {
        sharedPref
            .edit()
            .putBoolean(Preferences.HAS_PIN, hasPin)
            .apply()
    }

    override fun loadHasPin(): Boolean =
        sharedPref.getBoolean(Preferences.HAS_PIN, false)
}