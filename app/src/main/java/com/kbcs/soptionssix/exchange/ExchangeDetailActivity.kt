package com.kbcs.soptionssix.exchange

import android.content.ClipData
import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
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
    private val exchangeDetailViewModel: ExchangeDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_exchange_detail)
        binding.exchangeViewModel = exchangeDetailViewModel

        val bundle = Bundle()
        val naverMapFragment = NaverMapFragment()

        naverMapFragment.arguments = bundle.apply {
            putDouble("latitude", 37.5005)
            putDouble("longitude", 127.0281)
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fcv_naver_map, naverMapFragment)
            .commit()

        formatCost(9000)
        completeExchange()
        copyRoadClipData()
        copyAddressClipData()
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

    private fun copyRoadClipData() {
        binding.tvCopyRoad.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("copyData", binding.tvRoadDetail.text)
            // 클립보드에 배치
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "복사되었습니다.", Toast.LENGTH_SHORT).show()
            checkPaste(clipboard)
        }
    }

    private fun copyAddressClipData() {
        binding.tvCopyAddress.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("copyData", binding.tvAddressDetail.text)
            // 클립보드에 배치
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "복사되었습니다.", Toast.LENGTH_SHORT).show()
            checkPaste(clipboard)
        }
    }

    private fun checkPaste(clipManager: ClipboardManager) {
        when {
            !clipManager.hasPrimaryClip() -> false
            !(clipManager.primaryClipDescription?.hasMimeType(MIMETYPE_TEXT_PLAIN))!! -> false
            else -> true
        }
    }
}
