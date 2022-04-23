package com.stephan.androidtrivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.stephan.androidtrivia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //thie binding will replace the findViewById(R.id.any_view_id_here)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //drawerLayout = binding.drawerLayout

        //find the nav controller
        val navController = this.findNavController(R.id.myNavHostFragment)
        //setup the action bar menu with navigation drawer using the NavigationUI

        // NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController)

        //reference the id of the navigation_drawer from the xml layout.
        //NavigationUI.setupWithNavController(binding.navView, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
//        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}
