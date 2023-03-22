package dev.rockstar.portfolio.core.preferences.delegate

import androidx.core.content.edit
import dev.rockstar.portfolio.core.preferences.Preferences
import kotlin.reflect.KProperty

/**
 * It returns a `BooleanPreferenceDelegate` instance
 *
 * @param key The key of the preference.
 * @param defaultValue The default value of the preference.
 */
fun booleanPreferences(
    key: String,
    defaultValue: Boolean
) = BooleanPreferenceDelegate(key, defaultValue)

/* "When the getValue function is called on a Preferences object, return the value of the key property
from the sharedPreferences object."

The getValue function is called when the property is accessed. The property is accessed when the
property name is used in an expression */
class BooleanPreferenceDelegate(
    private val key: String,
    private val defaultValue: Boolean
) {

    /**
     * "When the getValue function is called on a Preferences object, return the value of the key
     * property from the sharedPreferences object."
     *
     * The getValue function is called when the property is accessed. The property is accessed when the
     * property name is used in an expression
     *
     * @param preferences Preferences - The Preferences object that contains the SharedPreferences
     * object.
     * @param property The property that is being delegated to.
     * @return A Boolean value
     */
    operator fun getValue(preferences: Preferences, property: KProperty<*>): Boolean {
        return preferences.sharedPreferences.getBoolean(key, defaultValue)
    }

    /**
     * `operator fun setValue(preferences: Preferences, property: KProperty<*>, value: Boolean?)`
     *
     * The `operator` keyword is used to define an operator function. The `setValue` function is the
     * name of the function. The `preferences: Preferences` is the first parameter of the function. The
     * `property: KProperty<*>` is the second parameter of the function. The `value: Boolean?` is the
     * third parameter of the function
     *
     * @param preferences Preferences - The Preferences object that contains the SharedPreferences
     * object
     * @param property KProperty<*> - This is the property that is being accessed.
     * @param value The value of the property.
     */
    operator fun setValue(preferences: Preferences, property: KProperty<*>, value: Boolean?) {
        if (value != null) {
            preferences.sharedPreferences.edit {
                putBoolean(key, value)
            }
        } else {
            preferences.sharedPreferences.edit {
                remove(key)
            }
        }
    }

}