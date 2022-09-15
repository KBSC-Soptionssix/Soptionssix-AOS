package com.kbcs.soptionssix.discount

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
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
            binding.ivStore.load(data.photo) {
                transformations(RoundedCornersTransformation(3f))
            }

            // 시간 비교하기
            timeCmp(data.discountStartTime, data)

            binding.root.setOnClickListener {
                itemClick(data)
            }
        }

        private fun timeCmp(
            discountStartTime: Int?,
            data: StoreDto
        ) {
            val currentTime = LocalDateTime.now()
            val curTime = currentTime.hour * 60 + currentTime.minute
            if (discountStartTime != null) {
                when {
                    discountStartTime > curTime -> {
                        binding.tvMaxDiscount.visibility = View.VISIBLE
                        binding.ivStore.setColorFilter(
                            Color.parseColor("#525252"),
                            PorterDuff.Mode.MULTIPLY
                        )
                        val startHour = discountStartTime / 60
                        val startMin = (discountStartTime % 60)
                        var min = startMin.toString()
                        if (startMin < 10) { // 10분 이전인 경우 앞에 0 있어야함
                            min = "0$startMin"
                        }
                        when {
                            discountStartTime < 720 -> {
                                binding.tvMaxDiscount.text = "$startHour:$min\n 할인 오픈예정"
                            }
                            else -> {
                                binding.tvMaxDiscount.text = "$startHour:$min\n 할인 오픈예정"
                            }
                        }
                    }
                    else -> {
                        binding.tvMaxDiscount.visibility = View.GONE
                        binding.root.setOnClickListener { // 오픈 됐으면 클릭이벤트 활성화
                            this.itemClick(data)
                        }
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
