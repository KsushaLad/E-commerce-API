package com.ksusha.e_commerceapi.fragment.home.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ksusha.e_commerceapi.hilt.repository.ProductsRepository
import com.ksusha.e_commerceapi.model.domain.Filter
import com.ksusha.e_commerceapi.model.domain.Product
import com.ksusha.e_commerceapi.redux.ApplicationState
import com.ksusha.e_commerceapi.redux.Store
import com.ksusha.e_commerceapi.redux.reducer.UiProductListReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    val store: Store<ApplicationState>,
    val uiProductListReducer: UiProductListReducer,
    private val productsRepository: ProductsRepository,
    private val filterGenerator: FilterGenerator
) : ViewModel() {
    fun refreshProducts() = viewModelScope.launch {
        val products: List<Product> = productsRepository.fetchAllProducts()
        val filters: Set<Filter> = filterGenerator.generateFrom(products)
        store.update { applicationState ->
            return@update applicationState.copy(
                products = products,
                productFilterInfo = ApplicationState.ProductFilterInfo(
                    filters = filters,
                    selectedFilter = null
                )
            )
        }
    }
}