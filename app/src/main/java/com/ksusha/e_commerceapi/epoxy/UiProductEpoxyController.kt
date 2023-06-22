package com.ksusha.e_commerceapi.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.ksusha.e_commerceapi.model.ui.UIProduct

class UiProductEpoxyController: TypedEpoxyController<List<UIProduct>>() {

    override fun buildModels(data: List<UIProduct>?) {
        if (data.isNullOrEmpty()) {
            repeat(7) {
                val epoxyId = it + 1
                UiProductEpoxyModel(uiProduct = null).id(epoxyId).addTo(this)
            }
            return
        }

        data.forEach { uiProduct ->
            UiProductEpoxyModel(uiProduct).id(uiProduct.product.id).addTo(this)
        }
    }
}