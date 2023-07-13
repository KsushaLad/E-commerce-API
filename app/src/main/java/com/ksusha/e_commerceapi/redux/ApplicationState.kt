package com.ksusha.e_commerceapi.redux

import com.ksusha.e_commerceapi.model.domain.Filter
import com.ksusha.e_commerceapi.model.domain.Product

data class ApplicationState(
    val products: List<Product> = emptyList(),
    val productFilterInfo: ProductFilterInfo = ProductFilterInfo(),
    val favouriteProductsId: Set<Int> = emptySet(),
    val expandedProductsId: Set<Int> = emptySet(),
    val inCartProductsId: Set<Int> = emptySet()
) {
    data class ProductFilterInfo(
        val filters: Set<Filter> = emptySet(),
        val selectedFilter: Filter? = null
    )
}
