package com.ksusha.e_commerceapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.ksusha.e_commerceapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.ui.setupActionBarWithNavController

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

    }

    private fun setupListeners() {
        /*binding.cardView.setOnClickListener {
         binding.productDescriptionTextView.apply {
             isVisible = !isVisible
         }
     }
     binding.addToCartButton.setOnClickListener {
         binding.inCartView.apply {
             isVisible = !isVisible
         }
     }
     var isFavorite = false
     binding.favoriteImageView.setOnClickListener {
         val imageRes = if (isFavorite) {
             R.drawable.ic_round_favorite_border_24
         } else {
             R.drawable.ic_round_favorite_24
         }
         binding.favoriteImageView.setIconResource(imageRes)
         isFavorite = !isFavorite
     }*/
        }

}