package com.example.shopdummy.network

import com.example.shopdummy.models.Product
import com.example.shopdummy.models.ProductModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): Response<ProductModel>

    @GET("products/{productId}")
    suspend fun getProductById(
        @Path(value = "productId") productId: String
    ): Response<Product>
}