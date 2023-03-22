package dev.rockstar.portfolio.ui.disclaimer

import com.skydoves.bindables.BindingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rockstar.portfolio.core.preferences.Preferences
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DisclaimerViewModel @Inject constructor(
    private val preferences: Preferences
) : BindingViewModel() {

    init {
        Timber.d("init DisclaimerViewModel")
    }

    fun isShowedDisclaimer(): Boolean {
        return preferences.showDisclaimer
    }

    fun showedDisclaimer() {
        preferences.showDisclaimer = false
    }

}