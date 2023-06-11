package com.ksusha.e_commerceapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ksusha.e_commerceapi.model.domain.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
): ViewModel() {

    private val _productLiveData = MutableLiveData<List<Product>>()
    val productsLiveData: LiveData<List<Product>> = _productLiveData

    fun refreshProducts() = viewModelScope.launch {
        val products = productsRepository.fetchAllProducts()
        _productLiveData.value = products
    }

}