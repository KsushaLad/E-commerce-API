package com.ksusha.e_commerceapi.redux.updater

import com.ksusha.e_commerceapi.redux.ApplicationState
import javax.inject.Inject

class UiProductInCartUpdater @Inject constructor() {

    fun update(productId: Int, currentState: ApplicationState): ApplicationState {
        val currentProductIdsInCart = currentState.inCartProductsId
        val newProductIdsInCart = if (currentProductIdsInCart.contains(productId)) {
            currentProductIdsInCart - productId
        } else {
            currentProductIdsInCart + productId
        }
        return currentState.copy(inCartProductsId = newProductIdsInCart)
    }
}