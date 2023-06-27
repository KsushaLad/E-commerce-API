package com.ksusha.e_commerceapi.epoxy

import androidx.lifecycle.viewModelScope
import com.airbnb.epoxy.TypedEpoxyController
import com.ksusha.e_commerceapi.fragment.list.ProductsListViewModel
import com.ksusha.e_commerceapi.model.ui.UIProduct
import kotlinx.coroutines.launch

class UiProductEpoxyController(
    private val viewModel: ProductsListViewModel
): TypedEpoxyController<List<UIProduct>>() {

    override fun buildModels(data: List<UIProduct>?) {
        if (data.isNullOrEmpty()) {
            repeat(7) {
                val epoxyId = it + 1
                UiProductEpoxyModel(
                    uiProduct = null,
                    onFavouriteIconClicked = ::onFavouriteIconClicked,
                    onUiProductClicked = ::onUiProductClicked
                ).id(epoxyId).addTo(this)
            }
            return
        }

        data.forEach { uiProduct ->
            UiProductEpoxyModel(
                uiProduct = uiProduct,
                onFavouriteIconClicked = ::onFavouriteIconClicked,
                onUiProductClicked = ::onUiProductClicked
            ).id(uiProduct.product.id).addTo(this)
        }
    }

    private fun onFavouriteIconClicked(selectedProductId: Int) {
        viewModel.viewModelScope.launch {
            viewModel.store.update { currentState ->
                val currentFavoriteIds = currentState.favouriteProductsId
                val newFavoriteIds = if (currentFavoriteIds.contains(selectedProductId)) {
                    currentFavoriteIds.filter { it != selectedProductId }.toSet()
                } else {
                    currentFavoriteIds + setOf(selectedProductId)
                }
                return@update currentState.copy(favouriteProductsId = newFavoriteIds)
            }
        }
    }

    private fun onUiProductClicked(productId: Int) {
        viewModel.viewModelScope.launch {
            viewModel.store.update { currentState ->
                val currentExpandedIds = currentState.expandedProductsId
                val newExpandedIds = if (currentExpandedIds.contains(productId)) {
                    currentExpandedIds.filter { it != productId }.toSet()
                } else {
                    currentExpandedIds + setOf(productId)
                }
                return@update currentState.copy(expandedProductsId = newExpandedIds)
            }
        }
    }

}