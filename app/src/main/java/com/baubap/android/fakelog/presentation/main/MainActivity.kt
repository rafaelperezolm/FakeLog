package com.baubap.android.fakelog.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.baubap.android.fakelog.R
import com.baubap.android.fakelog.databinding.ActivityMainBinding

// Activity that shows the main screen
class MainActivity : AppCompatActivity() {

    // ViewBinding associated to the current screen
    private lateinit var binding: ActivityMainBinding

    // Activity lifecycle functions
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
    }

    // Inits the current screen navigation components
    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment_container_view) as NavHostFragment
        navHostFragment.navController
    }

}