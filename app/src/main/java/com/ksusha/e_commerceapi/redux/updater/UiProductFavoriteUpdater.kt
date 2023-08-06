package com.ksusha.e_commerceapi.redux.updater

import com.ksusha.e_commerceapi.redux.ApplicationState
import javax.inject.Inject

class UiProductFavoriteUpdater @Inject constructor() {

    fun update(productId: Int, currentState: ApplicationState): ApplicationState {
        val currentFavoriteIds = currentState.favouriteProductsId
        val newFavoriteIds = if (currentFavoriteIds.contains(productId)) {
            currentFavoriteIds - productId
        } else {
            currentFavoriteIds + productId
        }
        return currentState.copy(favouriteProductsId = newFavoriteIds)
    }
}