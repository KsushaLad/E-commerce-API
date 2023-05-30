package com.ksusha.e_commerceapi.di.service

import com.ksusha.e_commerceapi.model.network.NetworkProduct
import retrofit2.Response
import retrofit2.http.GET

interface ProductsService {
    @GET("products")
    suspend fun getAllProducts(): Response<List<NetworkProduct>>
}