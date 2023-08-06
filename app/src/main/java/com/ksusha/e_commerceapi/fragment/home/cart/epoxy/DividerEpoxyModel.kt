package com.ksusha.e_commerceapi.fragment.home.cart.epoxy

import android.view.ViewGroup
import androidx.annotation.Dimension
import androidx.annotation.Dimension.Companion.PX
import androidx.core.view.updateLayoutParams
import com.ksusha.e_commerceapi.R
import com.ksusha.e_commerceapi.databinding.EpoxyModelDividerBinding
import com.ksusha.e_commerceapi.epoxy.ViewBindingKotlinModel

data class DividerEpoxyModel(
    @Dimension(unit = PX) private val horizontalMargin: Int = 0,
    @Dimension(unit = PX) private val verticalMargin: Int = 0
) : ViewBindingKotlinModel<EpoxyModelDividerBinding>(R.layout.epoxy_model_divider) {

    override fun EpoxyModelDividerBinding.bind() {
        root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            setMargins(horizontalMargin, verticalMargin, horizontalMargin, verticalMargin)
        }
    }
}