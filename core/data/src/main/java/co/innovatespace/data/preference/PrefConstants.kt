package co.innovatespace.data.preference

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey

object PrefConstants {
    val NIGHT_MODE = intPreferencesKey("night_mode")
    val IS_PAST_WIZARD = booleanPreferencesKey("is_past_wizard")
}