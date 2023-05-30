package com.ksusha.e_commerceapi.model.mapper

import com.ksusha.e_commerceapi.model.domain.Product
import com.ksusha.e_commerceapi.model.network.NetworkProduct
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

class ProductMapper @Inject constructor(){

    fun buildFrom(networkProduct: NetworkProduct): Product{
        return Product(
            category = networkProduct.category,
            description = networkProduct.description,
            id = networkProduct.id,
            image = networkProduct.image,
            price = BigDecimal(networkProduct.price).setScale(2, RoundingMode.HALF_UP) ,
            title = networkProduct.title
        )
    }

}