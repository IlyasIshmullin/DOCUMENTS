package com.example.documents_rewrite.mainApplication.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.core.view.*
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.documents_rewrite.R
import com.example.documents_rewrite.databinding.ActivityMainBinding
import com.google.android.material.color.DynamicColors
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.documentsFragment, R.id.favouriteFragment, R.id.profileFragment)
            .setOpenableLayout(binding.drawerLayout)
            .build()


        setSupportActionBar(binding.mainToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        setupActionBarWithNavController(navController, appBarConfiguration)

        visibilityNavElements(navController)
    }


    //Listen for the change in fragment (navigation) and hide or show drawer or bottom navigation accordingly if required
    private fun visibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            Log.d("MAINAPP", "INVISIBLITY_con")
            when (destination.id) {
                R.id.contactsFragment -> {
                    hideBothNavigation()
                }
                R.id.settingsFragment -> hideBottomNavigation()
                R.id.inviteFriendsFragment -> hideBottomNavigation()
                R.id.aboutDocumentsFragment -> hideBottomNavigation()
                else -> showBothNavigation()
            }
        }
    }

    //Hide both drawer and bottom navigation bar
    private fun hideBothNavigation() {
        binding.bottomNavigationView.visibility = View.GONE
        binding.navigationView.visibility = View.GONE
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED) //To lock navigation drawer so that it don't respond to swipe gesture
    }


    private fun hideBottomNavigation() { //Hide bottom navigation
        binding.bottomNavigationView.visibility = View.GONE
        binding.navigationView.visibility = View.VISIBLE
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED) //To unlock navigation drawer

        binding.navigationView.setupWithNavController(navController) //Setup Drawer navigation with navController
    }

    private fun showBothNavigation() {
        binding.bottomNavigationView.visibility = View.VISIBLE
        binding.navigationView.visibility = View.VISIBLE
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        setupNavControl() //To configure navController with drawer and bottom navigation
    }

    private fun setupNavControl() {
        Log.d("MAINAPP", "SETUPNAVCONTIOL")
        binding.navigationView.setupWithNavController(navController) //Setup Drawer navigation with navController
        binding.bottomNavigationView.setupWithNavController(navController) //Setup Bottom navigation with navController
    }


    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        when { //If drawer layout is open close that on back pressed
            binding.drawerLayout.isDrawerOpen(GravityCompat.START) -> {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            else -> {
                super.onBackPressed() //If drawer is already in closed condition then go back
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }


}
