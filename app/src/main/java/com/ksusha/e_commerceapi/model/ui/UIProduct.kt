package com.ksusha.e_commerceapi.model.ui

import com.ksusha.e_commerceapi.model.domain.Product

data class UIProduct(
    val product: Product,
    val isFavourite: Boolean = false,
    val isExpanded: Boolean = false,
)
