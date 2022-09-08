package com.kbcs.soptionssix.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kbcs.soptionssix.databinding.ItemDiscountProductBinding
import com.kbsc.data.dto.ProductDto

class DiscountProductAdapter :
    ListAdapter<ProductDto, DiscountProductAdapter.DiscountViewHolder>(DiscountProductDiffUtil()) {
    class DiscountViewHolder(private val binding: ItemDiscountProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ProductDto) {
            binding.discountProductItem = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscountViewHolder {
        val binding =
            ItemDiscountProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiscountViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class DiscountProductDiffUtil : DiffUtil.ItemCallback<ProductDto>() {
        override fun areItemsTheSame(
            oldItem: ProductDto,
            newItem: ProductDto
        ): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: ProductDto,
            newItem: ProductDto
        ): Boolean = oldItem == newItem
    }
}
