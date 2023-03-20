package dev.rockstar.portfolio.utils

import android.content.SharedPreferences
import com.google.gson.Gson
import dev.rockstar.portfolio.MainApp
import timber.log.Timber

class Preferences {

    fun setPreference(key: String?, value: Any) {
        try {
            val editor = MainApp.preferences.edit()
            when (value) {
                is String -> editor.putString(key, value)
                is Int -> editor.putInt(key, value)
                is Boolean -> editor.putBoolean(key, value)
                is Long -> editor.putLong(key, value)
                is Float -> editor.putFloat(key, value)
                else -> editor.putString(key, value.toString())
            }
            editor.apply()
        } catch (e: java.lang.Exception) {
            Timber.e(e.cause, "setPreference: ${e.message}")
        }
    }

    fun <T> setPreferenceObject(key: String?, y: T) {
        val editor: SharedPreferences.Editor = MainApp.preferences.edit()
        try {
            val gson = Gson()
            val inString = gson.toJson(y)
            editor.putString(key, inString)
            editor.apply()
        } catch (t: Throwable) {
            Timber.e(t.cause, "setPreferenceObject: ${t.message}")
        }
    }

    fun <T> getPreferenceObject(key: String?, c: Class<T>?): T? {
        return try {
            val gson = Gson()
            val value: String? = MainApp.preferences.getString(key, "")
            gson.fromJson(value, c)
        } catch (t: Throwable) {
            Timber.e(t.cause, "getPreferenceObject: ${t.message}")
            null
        }
    }

    fun <T> getPreference(key: String?, c: Class<T>): Any? {
        return try {
            when(c.name) {
                String::class.java.name -> {
                    MainApp.preferences.getString(key, null)
                }
                Int::class.java.name -> {
                    MainApp.preferences.getInt(key, -1)
                }
                Boolean::class.java.name -> {
                    MainApp.preferences.getBoolean(key, false)
                }
                Long::class.java.name -> {
                    MainApp.preferences.getLong(key, -1L)
                }
                Float::class.java.name -> {
                    MainApp.preferences.getFloat(key, -1F)
                }
                else -> {
                    MainApp.preferences.getString(key, null)
                }
            }
        } catch (e: Exception) {
            Timber.e(e.cause, "getPreference: ${e.message}")
            null
        }
    }

    fun deletePreferences(vararg keys: String?) {
        try {
            val editor = MainApp.preferences.edit()
            keys.forEach { editor.remove(it) }
            editor.apply()
        } catch (e: Exception) {
            Timber.e(e.cause, "deletePreferences: ${e.message}")
        }
    }


}