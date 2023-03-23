package dev.rockstar.portfolio.ui.main

import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint
import dev.rockstar.portfolio.R
import dev.rockstar.portfolio.databinding.LayoutMainBinding
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BindingActivity<LayoutMainBinding>(R.layout.layout_main) {

    @get:VisibleForTesting
    internal val viewModel: MainViewModel by viewModels()

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding {
            vm = viewModel
        }

        setSupportActionBar(binding.materialToolbar)
        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment?
                ?: return
        val navController = host.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBar(navController, appBarConfiguration)
        setupBottomNavMenu(navController)

        navController.addOnDestinationChangedListener { _, destination, arg ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                Timber.e(javaClass.name, e.message, e.cause)
                destination.id.toString()
            }
            Timber.d("MainActivity", "Navigated to $dest args: $arg")
        }

    }

    private fun setupBottomNavMenu(navController: NavController) {
        binding.bottomNav.setupWithNavController(navController)
    }

    private fun setupActionBar(
        navController: NavController,
        appBarConfiguration: AppBarConfiguration
    ) {
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.fragment_container)) || super.onOptionsItemSelected(
            item
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment_container).navigateUp(appBarConfiguration)
    }


}