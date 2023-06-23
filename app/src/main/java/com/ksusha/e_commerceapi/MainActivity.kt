package com.ksusha.e_commerceapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.ksusha.e_commerceapi.databinding.ActivityMainBinding
import com.ksusha.e_commerceapi.epoxy.UiProductEpoxyController
import com.ksusha.e_commerceapi.model.ui.UIProduct
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

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
        val controller = UiProductEpoxyController(viewModel)
        binding.epoxyRecyclerView.setController(controller)
        controller.setData(emptyList())

        combine(
            viewModel.store.stateFlow.map { it.products },
            viewModel.store.stateFlow.map { it.favouriteProductsId }
        ) { listOfProducts, setOfFavoriteIds ->
            listOfProducts.map { product ->
                UIProduct(product = product, isFavourite = setOfFavoriteIds.contains(product.id))
            }
        }.distinctUntilChanged().asLiveData().observe(this) { uiProducts ->
            controller.setData(uiProducts)
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