package dev.rockstar.portfolio.core.preferences

import android.content.SharedPreferences
import dev.rockstar.portfolio.core.preferences.delegate.booleanPreferences
import dev.rockstar.portfolio.core.preferences.delegate.stringPreferences
import javax.inject.Inject

class Preferences @Inject constructor(
    val sharedPreferences: SharedPreferences
) {

    var currency: String by stringPreferences(
        key = KEY_CURRENCY,
        defaultValue = String.EMPTY
    )

    var showDisclaimer: Boolean by booleanPreferences(
        key = KEY_SHOW_DISCLAIMER,
        defaultValue = true
    )

    companion object {
        private const val KEY_SHOW_DISCLAIMER = "key_show_disclaimer"
        private const val KEY_CURRENCY = "key_currency"
    }
}

val String.Companion.EMPTY
    inline get() = ""