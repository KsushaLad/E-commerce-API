package com.ksusha.e_commerceapi.redux.reducer

import kotlinx.coroutines.flow.Flow
import com.ksusha.e_commerceapi.model.ui.UIProduct
import com.ksusha.e_commerceapi.redux.ApplicationState
import com.ksusha.e_commerceapi.redux.Store
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UiProductListReducer @Inject constructor() {

    fun reduce(store: Store<ApplicationState>): Flow<List<UIProduct>> {
        return combine(
            store.stateFlow.map { it.products },
            store.stateFlow.map { it.favouriteProductsId },
            store.stateFlow.map { it.expandedProductsId },
            store.stateFlow.map { it.inCartProductsId }
        ) { listOfProducts, setOfFavoriteIds, setOfExpandedProductIds, inCartProductIds ->

            if (listOfProducts.isEmpty()) {
                return@combine emptyList<UIProduct>()
            }

            return@combine listOfProducts.map { product ->
                UIProduct(
                    product = product,
                    isFavourite = setOfFavoriteIds.contains(product.id),
                    isExpanded = setOfExpandedProductIds.contains(product.id),
                    isInCart = inCartProductIds.contains(product.id)
                )
            }
        }
    }

}