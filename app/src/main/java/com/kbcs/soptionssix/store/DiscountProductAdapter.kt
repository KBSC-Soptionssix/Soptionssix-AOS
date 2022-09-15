package com.kbcs.soptionssix.store

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.kbcs.soptionssix.databinding.ItemDiscountProductBinding
import com.kbsc.data.dto.ProductDto
import java.text.DecimalFormat

class DiscountProductAdapter(
    private val clickBuy: (ProductDto) -> (Unit),
    private val clickGive: (ProductDto) -> (Unit),
    private val clickWait: (ProductDto) -> (Unit)
) :
    ListAdapter<ProductDto, DiscountProductAdapter.DiscountViewHolder>(DiscountProductDiffUtil) {

    class DiscountViewHolder(
        private val binding: ItemDiscountProductBinding,
        private val clickBuy: (ProductDto) -> Unit,
        private val clickGive: (ProductDto) -> Unit,
        private val clickWait: (ProductDto) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ProductDto) {
            binding.discountProductItem = data
            binding.ivProduct.load(data.photo) {
                transformations(RoundedCornersTransformation(12f))
            }

            // 취소선
            binding.tvOriginalPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)

            if (data.donationCompleteCount == 0) {
                binding.tvCompleteNum.visibility = View.GONE
                binding.tvCompleteDonation.visibility = View.GONE
            }

            if (data.donationWaitCount == 0) {
                binding.tvWaitingNum.visibility = View.GONE
                binding.tvWaiting.visibility = View.GONE
                binding.ivWaitingPeople.visibility = View.GONE
            }

            val costFormatter = DecimalFormat("###,###")
            binding.tvOriginalPrice.text = costFormatter.format(data.price)
            val discountPrice: Int = data.price * (100 - data.discount) / 100 // 할인 가격 게산
            binding.tvDiscountPrice.text = costFormatter.format(discountPrice)
            binding.tvOriginalPrice.text = costFormatter.format(data.price)

            // tag 시간 계산
            val startHour: Int = data.storePreview.endTime / 60
            val startMin: Int = (data.storePreview.endTime % 60)
            var min = startMin.toString()
            if (startMin < 10) { // 10분 이전인 경우 앞에 0 있어야함
                min = "0$startMin"
            }

            when {
                startHour < 12 -> {
                    binding.tvTag1.text = "오전 $startHour" + "시 마감"
                }
                else -> {
                    binding.tvTag1.text = "오후 ${startHour - 12}" + "시 마감"
                }
            }

            // tag2
            val num = data.stockCount
            binding.tvTag2.text = "$num" + "개 남음"

            binding.btnBuy.setOnClickListener {
                clickBuy(data)
            }
            binding.btnDonation.setOnClickListener {
                clickGive(data)
            }
            binding.btnDonationWaiting.setOnClickListener {
                clickWait(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscountViewHolder {
        val binding =
            ItemDiscountProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscountViewHolder(binding, clickBuy, clickGive, clickWait)
    }

    override fun onBindViewHolder(holder: DiscountViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        private val DiscountProductDiffUtil = object : DiffUtil.ItemCallback<ProductDto>() {
            override fun areItemsTheSame(oldItem: ProductDto, newItem: ProductDto): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: ProductDto,
                newItem: ProductDto
            ): Boolean =
                oldItem.equals(newItem)
        }
    }
}
