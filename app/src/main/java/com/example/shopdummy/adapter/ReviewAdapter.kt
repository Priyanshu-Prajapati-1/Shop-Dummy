package com.example.shopdummy.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.shopdummy.databinding.ReviewItemBinding
import com.example.shopdummy.models.Review
import com.example.shopdummy.utils.formatDateTime
import com.example.shopdummy.utils.formatRating

class ReviewAdapter(
    private val context: Context,
    private val reviewList: List<Review>,
) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    inner class ViewHolder(var view: ReviewItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = reviewList[position]

        holder.view.apply {
            txtReviewerName.text = review.reviewerName
            txtReviewDate.text = formatDateTime(review.date)
            txtReviewComment.text = review.comment
            txtRating.text = formatRating(review.rating.toDouble())
        }

    }

    override fun getItemCount(): Int = reviewList.size

}