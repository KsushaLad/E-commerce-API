package com.ksusha.e_commerceapi

import com.ksusha.e_commerceapi.model.ui.UIFilter
import com.ksusha.e_commerceapi.model.ui.UIProduct

data class ProductsListFragmentUiState(
    val filters: Set<UIFilter>,
    val products: List<UIProduct>
)