package com.ksusha.e_commerceapi.fragment.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ksusha.e_commerceapi.ProductsRepository
import com.ksusha.e_commerceapi.model.domain.Filter
import com.ksusha.e_commerceapi.model.domain.Product
import com.ksusha.e_commerceapi.redux.ApplicationState
import com.ksusha.e_commerceapi.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    val store: Store<ApplicationState>,
    private val productsRepository: ProductsRepository
) : ViewModel() {
    fun refreshProducts() = viewModelScope.launch {
        val products: List<Product> = productsRepository.fetchAllProducts()
        store.update { applicationState ->
            return@update applicationState.copy(
                products = products,
                productFilterInfo = ApplicationState.ProductFilterInfo(
                    filters = products.map {
                        Filter(value = it.category, displayText = it.category)
                    }.toSet(),
                    selectedFilter = null
                )
            )
        }
    }
}