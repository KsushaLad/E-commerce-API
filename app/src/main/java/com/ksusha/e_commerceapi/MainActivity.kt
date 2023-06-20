package com.ksusha.e_commerceapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.ksusha.e_commerceapi.databinding.ActivityMainBinding
import com.ksusha.e_commerceapi.di.service.ProductsService
import com.ksusha.e_commerceapi.epoxy.ProductEpoxyController
import com.ksusha.e_commerceapi.model.domain.Product
import com.ksusha.e_commerceapi.model.mapper.ProductMapper
import com.ksusha.e_commerceapi.model.network.NetworkProduct
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val controller = ProductEpoxyController()
        binding.epoxyRecyclerView.setController(controller)
        controller.setData(emptyList())

        viewModel.store.stateFlow.map { applicationState ->
            applicationState.products
        }.distinctUntilChanged().asLiveData().observe(this) { products ->
            controller.setData(products)
        }
        viewModel.refreshProducts()

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