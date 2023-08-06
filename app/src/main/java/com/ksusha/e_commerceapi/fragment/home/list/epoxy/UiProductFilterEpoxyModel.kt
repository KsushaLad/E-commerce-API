package com.ksusha.e_commerceapi.fragment.home.list.epoxy

import androidx.core.content.ContextCompat
import com.ksusha.e_commerceapi.R
import com.ksusha.e_commerceapi.databinding.EpoxyModelProductFilterBinding
import com.ksusha.e_commerceapi.epoxy.ViewBindingKotlinModel
import com.ksusha.e_commerceapi.model.domain.Filter
import com.ksusha.e_commerceapi.model.ui.UIFilter

data class UiProductFilterEpoxyModel(
    val uiFilter: UIFilter,
    val onFilterSelected: (Filter) -> Unit
) : ViewBindingKotlinModel<EpoxyModelProductFilterBinding>(R.layout.epoxy_model_product_filter) {

    override fun EpoxyModelProductFilterBinding.bind() {
        root.setOnClickListener { onFilterSelected(uiFilter.filter) }
        filterNameTextView.text = uiFilter.filter.displayText

        val cardBackgroundColorResId = if (uiFilter.isSelected) {
            R.color.purple_500
        } else {
            R.color.purple_200
        }
        root.setCardBackgroundColor(ContextCompat.getColor(root.context, cardBackgroundColorResId))
    }
}
