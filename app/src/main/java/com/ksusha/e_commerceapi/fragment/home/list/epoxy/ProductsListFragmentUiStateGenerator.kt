package com.ksusha.e_commerceapi.fragment.home.list.epoxy

import com.ksusha.e_commerceapi.model.ui.UIFilter
import com.ksusha.e_commerceapi.model.ui.UIProduct
import com.ksusha.e_commerceapi.redux.ApplicationState
import javax.inject.Inject

class ProductsListFragmentUiStateGenerator @Inject constructor() {

    fun generate(
        uiProducts: List<UIProduct>,
        productFilterInfo: ApplicationState.ProductFilterInfo
    ): ProductsListFragmentUiState {
        if (uiProducts.isEmpty()) {
            return ProductsListFragmentUiState.Loading
        }

        val uiFilters = productFilterInfo.filters.map { filter ->
            UIFilter(
                filter = filter,
                isSelected = productFilterInfo.selectedFilter?.equals(filter) == true
            )
        }.toSet()

        val filteredProducts = if (productFilterInfo.selectedFilter == null) {
            uiProducts
        } else {
            uiProducts.filter { it.product.category == productFilterInfo.selectedFilter.value }
        }

        return ProductsListFragmentUiState.Success(uiFilters, filteredProducts)
    }
}