package com.ksusha.e_commerceapi.fragment.home.cart.epoxy

import androidx.lifecycle.viewModelScope
import com.airbnb.epoxy.TypedEpoxyController
import com.ksusha.e_commerceapi.epoxy.VerticalSpaceEpoxyModel
import com.ksusha.e_commerceapi.extensions.toPx
import com.ksusha.e_commerceapi.fragment.home.cart.CartFragment
import com.ksusha.e_commerceapi.fragment.home.cart.CartViewModel
import kotlinx.coroutines.launch

class CartFragmentEpoxyController(
    private val viewModel: CartViewModel,
    private val onEmptyStateClicked: () -> Unit
) : TypedEpoxyController<CartFragment.UiState>() {

    override fun buildModels(data: CartFragment.UiState?) {
        when (data) {
            null, is CartFragment.UiState.Empty -> {
                CartEmptyEpoxyModel(onClick = {
                    onEmptyStateClicked()
                }).id("empty_state").addTo(this)
            }
            is CartFragment.UiState.NonEmpty -> {
                data.products.forEachIndexed { index, uiProduct ->
                    addVerticalStyling(index)
                    CartItemEpoxyModel(
                        uiProduct = uiProduct,
                        horizontalMargin = 16.toPx(),
                        onFavoriteClicked = {
                            viewModel.viewModelScope.launch {
                                viewModel.store.update {
                                    return@update viewModel.uiProductFavoriteUpdater.update(
                                        productId = uiProduct.product.id,
                                        currentState = it
                                    )
                                }
                            }
                        },
                        onDeleteClicked = {
                            viewModel.viewModelScope.launch {
                                viewModel.store.update {
                                    return@update viewModel.uiProductInCartUpdater.update(
                                        productId = uiProduct.product.id,
                                        currentState = it
                                    )
                                }
                            }
                        }
                    ).id(uiProduct.product.id).addTo(this)
                }
            }
        }
    }

    private fun addVerticalStyling(index: Int) {
        VerticalSpaceEpoxyModel(8.toPx()).id("top_space_$index").addTo(this)
        if (index != 0) {
            DividerEpoxyModel(horizontalMargin = 16.toPx()).id("divider_$index").addTo(this)
        }
        VerticalSpaceEpoxyModel(8.toPx()).id("bottom_space_$index").addTo(this)
    }

}