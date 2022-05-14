package com.example.documents_rewrite.mainApplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.*
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.documents_rewrite.R
import com.example.documents_rewrite.databinding.ActivityMainBinding
import com.example.documents_rewrite.mainApplication.navigationDrawer.contacts.ContactsFragment
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


        /*setSupportActionBar(binding.mainToolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        setupWithNavController(binding.navigationView, navController)

        binding.navigationView.setNavigationItemSelectedListener(this)
        visibilityNavElements(navController)*/
        //binding.navigationView.setNavigationItemSelectedListener(this)

        setSupportActionBar(binding.mainToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        setupActionBarWithNavController(navController, appBarConfiguration)
        //binding.navigationView.setupWithNavController(navController)
        //setupActionBarWithNavController(navController, appBarConfiguration)
        //binding.navigationView.setupWithNavController(navController)

       /* val actionToggle = ActionBarDrawerToggle(
            this@MainActivity,
            binding.drawerLayout,
            R.string.open,
            R.string.close
        )

        binding.drawerLayout.addDrawerListener(actionToggle)
        actionToggle.syncState()
        binding.navigationView.setNavigationItemSelectedListener(this)

        */

        //binding.mainToolbar.setupWithNavController(navController, appBarConfiguration)

        /*val actionBarDrawerToggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.mainToolbar,
            R.string.open,
            R.string.close
        )
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        setupActionBarWithNavController(navController, appBarConfiguration)

         */

        //binding.bottomNavigationView.setupWithNavController(navController)
        visibilityNavElements(navController)
    }


    /*override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_contacts -> {
                Log.d("MAINAPP", "INVISIBLITY_item")
                Toast.makeText(this, "TOAST", Toast.LENGTH_LONG).show()
                hideBottomNavigation()
            }

            R.id.nav_settings -> {
                Toast.makeText(this, "TOAST_set", Toast.LENGTH_LONG).show()
                //hideBothNavigation()
            }
            R.id.nav_invite_frinends -> {
                hideBottomNavigation()
            }
            R.id.nav_about_documents -> {
                hideBottomNavigation()
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }*/


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
