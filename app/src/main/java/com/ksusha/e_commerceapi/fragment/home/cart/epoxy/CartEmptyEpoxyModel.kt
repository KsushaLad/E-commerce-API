package com.ksusha.e_commerceapi.fragment.home.cart.epoxy

import android.view.View
import com.ksusha.e_commerceapi.R
import com.ksusha.e_commerceapi.databinding.EpoxyModelCartEmptyBinding
import com.ksusha.e_commerceapi.epoxy.ViewBindingKotlinModel

data class CartEmptyEpoxyModel(
    private val onClick: (View) -> Unit
) : ViewBindingKotlinModel<EpoxyModelCartEmptyBinding>(R.layout.epoxy_model_cart_empty) {

    override fun EpoxyModelCartEmptyBinding.bind() {
        button.setOnClickListener(onClick)
    }
}