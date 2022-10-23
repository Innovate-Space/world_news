package co.innovatespace.worldnews

import android.os.Bundle
import android.view.Menu
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import co.innovatespace.worldnews.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navHostFragment.navController
    }

    private val appBarConfiguration by lazy {
        AppBarConfiguration(topLevelDestinationIds = setOf(
            R.id.nav_headlines,
            R.id.nav_source,
            R.id.nav_favorite,
        ), drawerLayout = binding.drawerLayout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }

        val navView: NavigationView = binding.navView
        navView.setupWithNavController(navController)
        setupUI()
    }

    private fun setupUI(){
        setupActionBarWithNavController(navController, appBarConfiguration)
        configureBottomNav()
        setNavigationListener()
    }

    private fun setNavigationListener(){
        navController.addOnDestinationChangedListener { _, _, arguments ->
            binding.appBarMain.contentMain.bottomNavigation.visibility =
                if( arguments?.getBoolean("ShowBottomNav", false)== true)  View.VISIBLE else View.GONE
            binding.drawerLayout.setDrawerLockMode(
                if( arguments?.getBoolean("ShowSidebar", false)== true) DrawerLayout.LOCK_MODE_UNLOCKED else DrawerLayout.LOCK_MODE_LOCKED_CLOSED
            )
        }
    }

    private fun configureBottomNav(){
        binding.appBarMain.contentMain.bottomNavigation.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}