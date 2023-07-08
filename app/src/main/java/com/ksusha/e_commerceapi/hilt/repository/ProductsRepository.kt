package com.ksusha.e_commerceapi.hilt.repository

import com.ksusha.e_commerceapi.di.service.ProductsService
import com.ksusha.e_commerceapi.model.domain.Product
import com.ksusha.e_commerceapi.model.mapper.ProductMapper
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val productsService: ProductsService,
    private val productMapper: ProductMapper
) {

    suspend fun fetchAllProducts(): List<Product>{
        return productsService.getAllProducts().body()?.let { networkProducts ->  
            networkProducts.map {
                productMapper.buildFrom(it)
            }
        } ?: emptyList()
    }

}