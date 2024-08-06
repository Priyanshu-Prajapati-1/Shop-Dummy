package com.example.shopdummy.screens.productList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopdummy.R
import com.example.shopdummy.adapter.ProductAdapter
import com.example.shopdummy.databinding.ActivityProductListBinding
import com.example.shopdummy.models.Product
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductListScreen : AppCompatActivity() {

    private lateinit var binding: ActivityProductListBinding
    private lateinit var adapter: ProductAdapter

    private val productListViewModel: ProductListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.progressBar.visibility = View.VISIBLE
        productListViewModel.products.observe(this@ProductListScreen) { data ->
            binding.progressBar.visibility = View.GONE
            if (data?.products != null) {
                setAdapter(data.products)
            } else {
                Toast.makeText(this, "No products found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setAdapter(productList: List<Product>) {
        adapter = ProductAdapter(this, productList)
        binding.rvProductsList.layoutManager = LinearLayoutManager(this)
        binding.rvProductsList.adapter = adapter
    }
}