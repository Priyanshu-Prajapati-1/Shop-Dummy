package com.example.shopdummy.repository

import com.example.shopdummy.models.Product
import com.example.shopdummy.models.ProductModel
import com.example.shopdummy.network.ProductApiService
import retrofit2.Response
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productApi: ProductApiService) {

    suspend fun getProducts(): ProductModel? {
        val response: Response<ProductModel> = productApi.getProducts()

        if (response.isSuccessful) {
            return response.body()
        } else {
            // Handle the error case
            throw Exception("Failed to fetch products: ${response.errorBody()?.string()}")
        }
    }

    suspend fun getProductById(productId: String): Product? {
        val response: Response<Product> = productApi.getProductById(productId)

        if (response.isSuccessful) {
            return response.body()
        } else {
            // Handle the error case
            throw Exception("Failed to fetch products: ${response.errorBody()?.string()}")
        }
    }

}