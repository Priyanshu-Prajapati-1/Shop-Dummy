package com.example.shopdummy.screens.productList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopdummy.models.ProductModel
import com.example.shopdummy.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productRepository: ProductRepository,
) :
    ViewModel() {

    val products: MutableLiveData<ProductModel> by lazy {
        MutableLiveData<ProductModel>()
    }

    init {
        viewModelScope.launch {
            val productList = productRepository.getProducts()
            products.value = productList
        }
    }

}