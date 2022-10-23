package co.innovatespace.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import co.innovatespace.data.preference.PrefConstants.IS_PAST_WIZARD
import co.innovatespace.data.preference.PrefConstants.NIGHT_MODE
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class PrefStoreManager  @Inject constructor(@ApplicationContext private val context: Context) {
    private val settingStore = context.dataStore

    suspend fun setTheme(mode:Int){
        settingStore.edit {settings ->
            settings[NIGHT_MODE] = mode
        }
    }

    val themeMode: Flow<Int> = settingStore.data.map {
        it[NIGHT_MODE] ?: -1
    }

    suspend fun setViewedWizard(seen: Boolean) {
        settingStore.edit { settings ->
            settings[IS_PAST_WIZARD] = seen
        }
    }

    val hasViewedWizard: Flow<Boolean> = settingStore.data.map {
        it[IS_PAST_WIZARD] ?: false
    }
}