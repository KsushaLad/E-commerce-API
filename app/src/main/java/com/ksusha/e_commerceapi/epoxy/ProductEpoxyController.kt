package com.ksusha.e_commerceapi.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.ksusha.e_commerceapi.model.domain.Product

class ProductEpoxyController: TypedEpoxyController<List<Product>>() {

    override fun buildModels(data: List<Product>?) {
        if (data == null || data.isEmpty()) {
            return
        }

        data.forEach { product ->
            ProductEpoxyModel(product).id(product.id).addTo(this)
        }
    }
}