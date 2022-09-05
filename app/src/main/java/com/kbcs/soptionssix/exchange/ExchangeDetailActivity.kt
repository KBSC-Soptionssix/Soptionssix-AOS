package com.kbcs.soptionssix.exchange

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.databinding.ActivityExchangeDetailBinding
import com.kbcs.soptionssix.navermap.NaverMapFragment
import java.text.DecimalFormat

class ExchangeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExchangeDetailBinding
    private val exchangeDetailViewModel by viewModels<ExchangeDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_exchange_detail)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<NaverMapFragment>(R.id.fcv_naver_map)
        }

        formatCost(9000)
        completeExchange()
    }

    private fun formatCost(costs: Int) {
        val costFormatter = DecimalFormat("###,###")

        binding.tvDiscountPrice.text = costFormatter.format(costs)
        binding.tvTotalPrice.text = costFormatter.format(costs)
    }

    private fun completeExchange() {
        binding.ivCompleteExchange.setOnClickListener {
            binding.ivCompleteExchange.background =
                getDrawable(R.drawable.shape_gray_fills_4_rectangle)

            binding.tvExchangeComplete.text = "교환"
            binding.tvRemainedTime.text = " "
            binding.tvRemained.text = "완료"
        }
    }
}
