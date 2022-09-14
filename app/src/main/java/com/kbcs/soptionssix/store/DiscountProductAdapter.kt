package com.kbcs.soptionssix.store

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
                transformations(RoundedCornersTransformation(3f))
            }

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
