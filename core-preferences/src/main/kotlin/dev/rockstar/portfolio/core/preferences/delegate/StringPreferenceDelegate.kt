package dev.rockstar.portfolio.core.preferences.delegate

import androidx.core.content.edit
import dev.rockstar.portfolio.core.preferences.Preferences
import kotlin.reflect.KProperty

/**
 * It returns a `StringPreferenceDelegate` instance that uses the given `key` and `defaultValue`
 * parameters
 *
 * @param key The key to store the preference under.
 * @param defaultValue The default value for the preference.
 */
fun stringPreferences(key: String, defaultValue: String) =
    StringPreferenceDelegate(key, defaultValue)

/* "When the value of a property is set, store the value in the SharedPreferences object."

The first parameter is the object that contains the property. In this case, it's the Preferences
object. The second parameter is the property itself. The third parameter is the value that's being
assigned to the property */
class StringPreferenceDelegate(
    private val key: String,
    private val defaultValue: String
) {

    /**
     * If the key exists in the SharedPreferences, return the value, otherwise set the default value
     * and return it
     *
     * @param preferences Preferences - The Preferences object that contains the SharedPreferences
     * object
     * @param property KProperty<*> - This is the property that is being accessed.
     * @return The value of the key in the shared preferences, or the default value if the key is not
     * found.
     */
    operator fun getValue(preferences: Preferences, property: KProperty<*>): String {
        return preferences.sharedPreferences.getString(key, null) ?: let {
            setValue(preferences, property, defaultValue)
            defaultValue
        }
    }

    /**
     * "When the value of a property is set, store the value in the SharedPreferences object."
     *
     * The first parameter is the object that contains the property. In this case, it's the Preferences
     * object. The second parameter is the property itself. The third parameter is the value that's
     * being assigned to the property
     *
     * @param preferences Preferences - The Preferences object that is calling this function.
     * @param property KProperty<*> - This is the property that is being accessed.
     * @param value The value of the property.
     */
    operator fun setValue(preferences: Preferences, property: KProperty<*>, value: String) {
        preferences.sharedPreferences.edit { putString(key, value) }
    }

}