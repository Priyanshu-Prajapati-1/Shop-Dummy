package com.example.shopdummy.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopdummy.R
import com.example.shopdummy.databinding.ProductListItemBinding
import com.example.shopdummy.models.Product
import com.example.shopdummy.screens.productView.ProductViewScreen
import com.example.shopdummy.utils.formatRating

class ProductAdapter(
    private val context: Context,
    private val productList: List<Product>,
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var lastPosition = -1

    inner class ViewHolder(var view: ProductListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val product = productList[position]

        setViews(holder, product)
        setAnimation(holder.view.llListItem, position)
    }

    private fun setViews(holder: ViewHolder, product: Product) {

        holder.view.apply {
            txtProductTitle.text = product.title
            txtProductRating.text = formatRating(product.rating)
            txtProductAvailability.text = product.availabilityStatus
            txtDiscount.text =
                context.getString(R.string.percentage_format, product.discountPercentage)
            txtPrice.text = context.getString(R.string.product_price, product.price)
            Glide.with(context)
                .load(product.images[0])
                .placeholder(R.drawable.loader)
                .into(imgProductImage)

            llListItem.setOnClickListener {
                val intent = Intent(context, ProductViewScreen::class.java)
                intent.putExtra("productId", product.id.toString())
                context.startActivity(intent)
            }
        }
    }

    private fun setAnimation(llListItem: LinearLayout, position: Int) {

        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
            llListItem.startAnimation(animation)
            lastPosition = position
        }
    }
}