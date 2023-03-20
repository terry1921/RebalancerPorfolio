package dev.rockstar.portfolio.ui.disclaimer

import com.skydoves.bindables.BindingViewModel
import dev.rockstar.portfolio.utils.Preferences
import timber.log.Timber
import javax.inject.Inject

const val DISCLAIMER = "disclaimer"

class DisclaimerViewModel @Inject constructor() : BindingViewModel() {

    init {
        Timber.d("init DisclaimerViewModel")
    }

    fun isShowedDisclaimer(): Boolean {
        val show = Preferences().getPreference(DISCLAIMER, Boolean::class.java) ?: false
        return show as Boolean
    }

    fun showedDisclaimer() {
        Preferences().setPreference(DISCLAIMER, true)
    }

}