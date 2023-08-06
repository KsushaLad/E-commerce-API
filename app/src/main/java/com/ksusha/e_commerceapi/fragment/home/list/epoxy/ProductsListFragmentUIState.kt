package com.ksusha.e_commerceapi.fragment.home.list.epoxy

import com.ksusha.e_commerceapi.model.ui.UIFilter
import com.ksusha.e_commerceapi.model.ui.UIProduct

sealed interface ProductsListFragmentUiState {

    data class Success(
        val filters: Set<UIFilter>,
        val products: List<UIProduct>
    ): ProductsListFragmentUiState

    object Loading: ProductsListFragmentUiState
}