package co.innovatespace.worldnews

import android.app.Application
import co.innovatespace.data.preference.PrefStoreManager
import co.innovatespace.ui.ThemeHelper
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class NewsApp: Application() {
    @Inject
    lateinit var prefStoreManager: PrefStoreManager

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() = GlobalScope.launch(Dispatchers.Main) {
        // Todo("This should be refactored to be passed through the domain layer")
        prefStoreManager.themeMode.flowOn(Dispatchers.IO).collect{
            ThemeHelper.applyTheme( it )

        }

    }
}