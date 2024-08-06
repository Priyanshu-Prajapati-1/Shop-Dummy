package com.example.shopdummy.screens.productView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopdummy.models.Product
import com.example.shopdummy.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewViewModel @Inject constructor(
    private val productRepository: ProductRepository,
) :
    ViewModel() {

    val product: MutableLiveData<Product> by lazy {
        MutableLiveData<Product>()
    }

    fun getProductById(productId: String) {
        viewModelScope.launch {
            val productById = productRepository.getProductById(productId)
            product.value = productById
        }
    }
}