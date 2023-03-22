package dev.rockstar.portfolio.utils

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import dev.rockstar.portfolio.MainApp
import timber.log.Timber

class Preferences {

    private val preferences: SharedPreferences? by lazy {
        MainApp.mContext?.getSharedPreferences(CONFIG_FILE_NAME, MODE_PRIVATE)
    }

    companion object {
        const val CONFIG_FILE_NAME = "rebalancer"
    }

    init {
        Timber.d("init Utils -> Preferences")
    }

    fun setPreference(key: String?, value: Any) {
        try {
            val editor = preferences?.edit()
            editor?.apply {
                when (value) {
                    is String -> putString(key, value)
                    is Int -> putInt(key, value)
                    is Boolean -> putBoolean(key, value)
                    is Long -> putLong(key, value)
                    is Float -> putFloat(key, value)
                    else -> putString(key, value.toString())
                }
                apply()
            }
        } catch (e: java.lang.Exception) {
            Timber.e(e.cause, "setPreference: ${e.message}")
        }
    }

    fun <T> setPreferenceObject(key: String?, y: T) {
        val editor = preferences?.edit()
        try {
            val gson = Gson()
            val inString = gson.toJson(y)
            editor?.apply {
                putString(key, inString)
                apply()
            }
        } catch (t: Throwable) {
            Timber.e(t.cause, "setPreferenceObject: ${t.message}")
        }
    }

    fun <T> getPreferenceObject(key: String?, c: Class<T>?): T? {
        return try {
            val gson = Gson()
            val value: String? = preferences?.getString(key, "")
            gson.fromJson(value, c)
        } catch (t: Throwable) {
            Timber.e(t.cause, "getPreferenceObject: ${t.message}")
            null
        }
    }

    fun <T> getPreference(key: String?, c: Class<T>): Any? {
        return try {
            when (c.name) {
                String::class.java.name -> {
                    preferences?.getString(key, null)
                }
                Int::class.java.name -> {
                    preferences?.getInt(key, -1)
                }
                Boolean::class.java.name -> {
                    preferences?.getBoolean(key, false)
                }
                Long::class.java.name -> {
                    preferences?.getLong(key, -1L)
                }
                Float::class.java.name -> {
                    preferences?.getFloat(key, -1F)
                }
                else -> {
                    preferences?.getString(key, null)
                }
            }
        } catch (e: Exception) {
            Timber.e(e.cause, "getPreference: ${e.message}")
            null
        }
    }

    fun deletePreferences(vararg keys: String?) {
        try {
            val editor = preferences?.edit()
            editor?.apply {
                keys.forEach { remove(it) }
                apply()
            }
        } catch (e: Exception) {
            Timber.e(e.cause, "deletePreferences: ${e.message}")
        }
    }

}