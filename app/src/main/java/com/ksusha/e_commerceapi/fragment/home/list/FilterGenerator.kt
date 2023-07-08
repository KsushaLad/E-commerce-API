package com.ksusha.e_commerceapi.fragment.home.list

import com.ksusha.e_commerceapi.model.domain.Filter
import com.ksusha.e_commerceapi.model.domain.Product
import javax.inject.Inject

class FilterGenerator @Inject constructor() {

    fun generateFrom(productsList: List<Product>): Set<Filter> {
        return productsList.groupBy {
            it.category
        }.map { mapEntry ->
            Filter(value = mapEntry.key, displayText = "${mapEntry.key} (${mapEntry.value.size})")
        }.toSet()
    }
}