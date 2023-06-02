package com.ksusha.e_commerceapi.epoxy

import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import coil.load
import com.ksusha.e_commerceapi.R
import com.ksusha.e_commerceapi.databinding.EpoxyModelProductItemBinding
import com.ksusha.e_commerceapi.model.domain.Product
import java.text.NumberFormat

data class ProductEpoxyModel(
    val product: Product?
) : ViewBindingKotlinModel<EpoxyModelProductItemBinding>(R.layout.epoxy_model_product_item) {

    private val currencyFormatter = NumberFormat.getCurrencyInstance()

    override fun EpoxyModelProductItemBinding.bind() {

        shimmerLayout.isVisible = product == null
        cardView.isInvisible = product == null

        product?.let { product ->
            shimmerLayout.stopShimmer()
            productTitleTextView.text = product.title
            productDescriptionTextView.text = product.description
            productCategoryTextView.text = product.category
            productPriceTextView.text = currencyFormatter.format(product.price)
            productImageViewLoadingProgressBar.isVisible = true
            productImageView.load(data = product.image) {
                listener { request, result ->
                    productImageViewLoadingProgressBar.isGone = true
                }
            }
        } ?: shimmerLayout.startShimmer()
    }
}
