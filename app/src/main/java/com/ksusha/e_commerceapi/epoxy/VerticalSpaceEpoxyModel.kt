package com.ksusha.e_commerceapi.epoxy

import android.view.ViewGroup
import androidx.annotation.Dimension
import androidx.annotation.Dimension.Companion.PX
import com.ksusha.e_commerceapi.R
import com.ksusha.e_commerceapi.databinding.EpoxyModelVerticalSpaceBinding

data class VerticalSpaceEpoxyModel(
    @Dimension(unit = PX) val height: Int
) : ViewBindingKotlinModel<EpoxyModelVerticalSpaceBinding>(R.layout.epoxy_model_vertical_space) {

    override fun EpoxyModelVerticalSpaceBinding.bind() {
        root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, height
        )
    }
}