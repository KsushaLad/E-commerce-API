package com.ksusha.e_commerceapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ksusha.e_commerceapi.model.domain.Product
import com.ksusha.e_commerceapi.redux.ApplicationState
import com.ksusha.e_commerceapi.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    val store: Store<ApplicationState>
): ViewModel() {

    fun refreshProducts() = viewModelScope.launch {
        val products: List<Product> = productsRepository.fetchAllProducts()
        store.update { applicationState ->
            return@update applicationState.copy(
                products = products
            )
        }
    }

}