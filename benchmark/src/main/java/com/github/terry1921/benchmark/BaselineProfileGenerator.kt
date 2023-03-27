package com.github.terry1921.benchmark

import androidx.benchmark.macro.ExperimentalBaselineProfilesApi
import androidx.benchmark.macro.junit4.BaselineProfileRule
import org.junit.Rule
import org.junit.Test

@ExperimentalBaselineProfilesApi
class BaselineProfileGenerator {

    @get:Rule
    val baselineProfileRule = BaselineProfileRule()

    @Test
    fun startup() = baselineProfileRule.collectBaselineProfile(
        packageName = "dev.rockstar.portfolio"
    ) {
        pressHome()
        startActivityAndWait()
        device.waitForIdle()
    }

}