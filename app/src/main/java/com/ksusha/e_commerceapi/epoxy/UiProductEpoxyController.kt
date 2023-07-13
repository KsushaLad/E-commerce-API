package com.ksusha.e_commerceapi.epoxy

import androidx.lifecycle.viewModelScope
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.TypedEpoxyController
import com.ksusha.e_commerceapi.fragment.home.list.ProductsListFragmentUiState
import com.ksusha.e_commerceapi.epoxy.filter.UiProductFilterEpoxyModel
import com.ksusha.e_commerceapi.extensions.toPx
import com.ksusha.e_commerceapi.fragment.home.list.ProductsListViewModel
import com.ksusha.e_commerceapi.model.domain.Filter
import kotlinx.coroutines.launch
import java.util.*

class UiProductEpoxyController(
    private val viewModel: ProductsListViewModel
): TypedEpoxyController<ProductsListFragmentUiState>() {

    override fun buildModels(data: ProductsListFragmentUiState?) {
        when (data) {
            is ProductsListFragmentUiState.Success -> {
                val uiFilterModels = data.filters.map { uiFilter ->
                    UiProductFilterEpoxyModel(
                        uiFilter = uiFilter,
                        onFilterSelected = ::onFilterSelected
                    ).id(uiFilter.filter.value)
                }
                CarouselModel_()
                    .models(uiFilterModels)
                    .padding(Carousel.Padding(16.toPx(), 8.toPx()))
                    .id("filters")
                    .addTo(this)
                data.products.forEach { uiProduct ->
                    UiProductEpoxyModel(
                        uiProduct = uiProduct,
                        onFavouriteIconClicked = ::onFavouriteIconClicked,
                        onUiProductClicked = ::onUiProductClicked,
                        onAddToCartClicked = ::onAddToCartClicked
                    ).id(uiProduct.product.id).addTo(this)
                }
            }

            is ProductsListFragmentUiState.Loading -> {
                repeat(7) {
                    val epoxyId = UUID.randomUUID().toString()
                    UiProductEpoxyModel(
                        uiProduct = null,
                        onFavouriteIconClicked = ::onFavouriteIconClicked,
                        onUiProductClicked = ::onUiProductClicked,
                        onAddToCartClicked = ::onAddToCartClicked
                    ).id(epoxyId).addTo(this)
                }
            }

            else -> {
                throw RuntimeException("Unhandled branch! $data")
            }

        }
    }

    private fun onAddToCartClicked(productId: Int) {
        viewModel.viewModelScope.launch {
            viewModel.store.update { currentState ->
                val currentProductIdsInCart = currentState.inCartProductsId
                val newProductIdsInCart = if (currentProductIdsInCart.contains(productId)) {
                    currentProductIdsInCart.filter { it != productId }.toSet()
                } else {
                    currentProductIdsInCart + setOf(productId)
                }
                return@update currentState.copy(inCartProductsId = newProductIdsInCart)
            }
        }
    }

        private fun onFilterSelected(filter: Filter) {
            viewModel.viewModelScope.launch {
                viewModel.store.update { currentState ->
                    val currentlySelectedFilter = currentState.productFilterInfo.selectedFilter
                    return@update currentState.copy(
                        productFilterInfo = currentState.productFilterInfo.copy(
                            selectedFilter = if (currentlySelectedFilter != filter) {
                                filter
                            } else {
                                null
                            }
                        )
                    )
                }
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

        fun onUiProductClicked(productId: Int) {
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


