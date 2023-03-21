package dev.rockstar.portfolio

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApp : Application() {

    var mContext: Context? = null

    override fun onCreate() {
        super.onCreate()
        preferences = getSharedPreferences(CONFIG_FILE_NAME, MODE_PRIVATE)
        mContext = this
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    companion object {
        lateinit var preferences: SharedPreferences
        const val CONFIG_FILE_NAME: String = BuildConfig.CONF
    }

}