package com.kbcs.soptionssix.discount

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kbcs.soptionssix.databinding.ItemDiscountStoreBinding
import com.kbsc.data.dto.StoreDto

class DiscountStoreAdapter(private val itemClick: (StoreDto) -> Unit) :
    ListAdapter<StoreDto, DiscountStoreAdapter.DiscountStoreViewHolder>(DiscountStoreDiffUtil) {

    class DiscountStoreViewHolder(
        private val binding: ItemDiscountStoreBinding,
        private val itemClick: (StoreDto) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: StoreDto) {
            binding.discountStoreItem = data
            binding.root.setOnClickListener {
                itemClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscountStoreViewHolder {
        val binding =
            ItemDiscountStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscountStoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiscountStoreViewHolder, position: Int) {
        holder.onBind(getItem(position), itemClick)
    }

    companion object {
        private val DiscountStoreDiffUtil = object : DiffUtil.ItemCallback<StoreDto>() {
            override fun areItemsTheSame(oldItem: StoreDto, newItem: StoreDto): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: StoreDto,
                newItem: StoreDto
            ): Boolean =
                oldItem.equals(newItem)
        }
    }
}
