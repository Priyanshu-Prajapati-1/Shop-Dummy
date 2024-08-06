package com.example.shopdummy.screens.productView

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.example.shopdummy.R
import com.example.shopdummy.adapter.ProductAdapter
import com.example.shopdummy.adapter.ReviewAdapter
import com.example.shopdummy.databinding.ActivityProductViewScreenBinding
import com.example.shopdummy.models.Product
import com.example.shopdummy.models.Review
import com.example.shopdummy.utils.formatRating
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductViewScreen : AppCompatActivity() {

    private lateinit var binding: ActivityProductViewScreenBinding
    private lateinit var adapter: ReviewAdapter

    private val productViewViewModel: ProductViewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductViewScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.progressBar.visibility = View.VISIBLE
        val productId = intent.getStringExtra("productId")

        if (productId != null) {
            productViewViewModel.getProductById(productId)
        } else {
            showError("Invalid product ID")
        }

        productViewViewModel.product.observe(this) { product ->
            binding.progressBar.visibility = View.GONE
            binding.frame.visibility = View.VISIBLE
            if (product != null) {
                setProductDetails(product)
                setAdapter(product.reviews)
            } else {
                showError("Product not found")
            }
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }

    private fun setAdapter(reviewList: List<Review>) {
        adapter = ReviewAdapter(this, reviewList)
        binding.rvReviewList.layoutManager = LinearLayoutManager(this)
        binding.rvReviewList.adapter = adapter
    }

    private fun setProductDetails(product: Product?) {
        val imageLink = product?.images?.get(0) ?: ""
        binding.apply {
            Glide.with(this@ProductViewScreen)
                .load(imageLink)
                .placeholder(R.drawable.loader)
                .into(binding.imgProduct)
        }

        binding.txtTitle.text = product?.title ?: ""
        binding.txtDescription.text = product?.description ?: "No description available"
        binding.txtDiscount.text = getString(
            R.string.percentage_format,
            product?.discountPercentage?.times((-1)) ?: 0.0
        )
        binding.txtPrice.text = getString(R.string.product_price, product?.price ?: 0.0)
        binding.txtBrand.text = getString(R.string.brand_format, product?.brand ?: "Unknown")
        binding.txtReturn.text = product?.returnPolicy ?: "No return policy available"
        binding.txtRating.text = formatRating(product?.rating ?: 0.0)
        binding.txtMinOrder.text =
            getString(R.string.min_order, product?.minimumOrderQuantity.toString())
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}