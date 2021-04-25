package com.example.trackmyrun.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.trackmyrun.R
import com.example.trackmyrun.db.RunDao
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())
        navHostFragment.findNavController()
            .addOnDestinationChangedListener { controller, destination, arguments ->
                bottomNavigationView.visibility = when (destination.id) {
                    R.id.settingsFragment, R.id.runFragment, R.id.statisticsFragment -> View.VISIBLE
                    else -> View.INVISIBLE
                }
            }
    }
}