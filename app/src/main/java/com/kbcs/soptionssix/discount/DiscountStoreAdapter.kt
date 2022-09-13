package com.kbcs.soptionssix.discount

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kbcs.soptionssix.databinding.ItemDiscountStoreBinding
import com.kbsc.data.dto.StoreDto
import java.time.LocalDateTime

class DiscountStoreAdapter(private val itemClick: (StoreDto) -> Unit) :
    ListAdapter<StoreDto, DiscountStoreAdapter.DiscountStoreViewHolder>(DiscountStoreDiffUtil) {

    class DiscountStoreViewHolder(
        private val binding: ItemDiscountStoreBinding,
        private val itemClick: (StoreDto) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: StoreDto) {
            binding.discountStoreItem = data

            // 시간 비교하기
            timeCmp(data.discountStartTime)

            binding.root.setOnClickListener {
                itemClick(data)
            }
        }

        private fun timeCmp(discountStartTime: Int?) {
            val currentTime = LocalDateTime.now()
            val curTime = currentTime.hour * 60 + currentTime.minute
            if (discountStartTime != null) {
                when {
                    discountStartTime > curTime -> {
                        binding.tvMaxDiscount.visibility = View.VISIBLE
                        val startHour = discountStartTime / 60
                        val startMin = discountStartTime % 60
                        when {
                            discountStartTime < 720 -> {
                                binding.tvMaxDiscount.text = "오전 $startHour:$startMin\n 할인 오픈예정"
                            }
                            else -> {
                                binding.tvMaxDiscount.text = "오후 $startHour:$startMin\n 할인 오픈예정"
                            }
                        }
                    }
                    else -> {
                        binding.tvMaxDiscount.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscountStoreViewHolder {
        val binding =
            ItemDiscountStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscountStoreViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: DiscountStoreViewHolder, position: Int) {
        holder.onBind(getItem(position))
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
