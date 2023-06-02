package com.ksusha.e_commerceapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isGone
import androidx.core.view.isVisible
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
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var productService: ProductsService

    @Inject
    lateinit var productMapper: ProductMapper

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val controller = ProductEpoxyController()
        binding.epoxyRecyclerView.setController(controller)
        controller.setData(emptyList())
        lifecycleScope.launchWhenStarted {
            val response: Response<List<NetworkProduct>> = productService.getAllProducts()
            val domainProducts: List<Product> = response.body()?.map {
                productMapper.buildFrom(networkProduct = it)
            } ?: emptyList()
            controller.setData(domainProducts)
            if (domainProducts.isEmpty()) {
                Snackbar.make(binding.root, "Failed to fetch", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setupListeners() {
            /*binding.cardView.setOnClickListener {
                binding.productDescriptionTextView.apply {
                    isVisible = !isVisible
                }
    @@ -70,6 +67,6 @@ class MainActivity : AppCompatActivity() {
                }
                binding.favoriteImageView.setIconResource(imageRes)
                isFavorite = !isFavorite
            }
            }*/
        }

}