package com.ksusha.e_commerceapi.redux

import com.ksusha.e_commerceapi.model.domain.Product

data class ApplicationState(
    val products: List<Product> = emptyList(),
    val favouriteProductsId: Set<Int> = emptySet(),
    val expandedProductsId: Set<Int> = emptySet()
)
