package com.ksusha.e_commerceapi.fragment.home.cart.epoxy

import android.view.ViewGroup
import androidx.annotation.Dimension
import androidx.core.view.updateLayoutParams
import coil.load
import com.ksusha.e_commerceapi.R
import com.ksusha.e_commerceapi.databinding.EpoxyModelCartProductItemBinding
import com.ksusha.e_commerceapi.epoxy.ViewBindingKotlinModel
import com.ksusha.e_commerceapi.model.ui.UIProduct

data class CartItemEpoxyModel(
    private val uiProduct: UIProduct,
    @Dimension(unit = Dimension.PX) private val horizontalMargin: Int,
    private val onFavoriteClicked: () -> Unit,
    private val onDeleteClicked: () -> Unit
) : ViewBindingKotlinModel<EpoxyModelCartProductItemBinding>(R.layout.epoxy_model_cart_product_item) {

    override fun EpoxyModelCartProductItemBinding.bind() {
        productTitleTextView.text = uiProduct.product.title
        val imageRes = if (uiProduct.isFavourite) {
            R.drawable.ic_round_favorite_24
        } else {
            R.drawable.ic_round_favorite_border_24
        }
        favoriteImageView.setIconResource(imageRes)
        favoriteImageView.setOnClickListener { onFavoriteClicked() }

        deleteIconImageView.setOnClickListener { onDeleteClicked() }
        productImageView.load(data = uiProduct.product.image)

        root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            setMargins(horizontalMargin, 0, horizontalMargin, 0)
        }
        quantityView.apply {
            quantityTextView.text = 9.toString()
            minusImageView.setOnClickListener {  }
            plusImageView.setOnClickListener {  }
        }
    }
}