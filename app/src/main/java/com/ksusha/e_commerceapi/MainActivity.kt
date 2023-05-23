package com.ksusha.e_commerceapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val productService = retrofit.create(ProductService::class.java)

        lifecycleScope.launchWhenStarted {
            val response = productService.getAllProducts()
            Log.d("DATA", response.body()!!.toString())
        }
    }

    interface ProductService {
        @GET("products")
        suspend fun getAllProducts(): Response<List<Any>>
    }

}