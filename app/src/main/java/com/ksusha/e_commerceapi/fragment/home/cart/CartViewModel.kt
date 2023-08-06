package com.ksusha.e_commerceapi.fragment.home.cart

import androidx.lifecycle.ViewModel
import com.ksusha.e_commerceapi.redux.ApplicationState
import com.ksusha.e_commerceapi.redux.Store
import com.ksusha.e_commerceapi.redux.reducer.UiProductListReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    val store: Store<ApplicationState>,
    val uiProductListReducer: UiProductListReducer
): ViewModel() {
}