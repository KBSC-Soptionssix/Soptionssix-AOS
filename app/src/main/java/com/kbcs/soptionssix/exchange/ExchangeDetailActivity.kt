package com.kbcs.soptionssix.exchange

import android.content.ClipData
import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.databinding.ActivityExchangeDetailBinding
import com.kbcs.soptionssix.navermap.NaverMapFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ExchangeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExchangeDetailBinding
    private val exchangeDetailViewModel: ExchangeDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_exchange_detail)
        val receiptId = intent.getStringExtra("receiptId") ?: ""

        lifecycleScope.launch {
            exchangeDetailViewModel.fetchExchangeDetailList(receiptId)
        }

        completeExchange()
        copyRoadClipData()
        copyAddressClipData()
        exchangeObserver()
    }

    private fun exchangeObserver() {
        exchangeDetailViewModel.exchangeList.observe(this) {
            val costFormatter = DecimalFormat("###,###")
            binding.exchangeViewModel = it

            val discountPrice = it.product.price * it.product.discount / 100
            val totalPrice = it.product.price - discountPrice
            binding.tvDiscountPrice.text = costFormatter.format(discountPrice)
            binding.tvTotalPrice.text = costFormatter.format(totalPrice)

            val bundle = Bundle()
            val naverMapFragment = NaverMapFragment()

            naverMapFragment.arguments = bundle.apply {
                putDouble("latitude", it.store.mapX.toDouble())
                putDouble("longitude", it.store.mapY.toDouble())
            }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fcv_naver_map, naverMapFragment)
                .commit()
        }

        exchangeDetailViewModel.endTimeList.observe(this) {
            val date = Date()
            date.time = it.pickUpTime * 1000L
            val dayFormatter = SimpleDateFormat("EE")
            val dayDividerFormatter = SimpleDateFormat("aa")
            val formatter =
                SimpleDateFormat(
                    "yyyy년 MM월 dd일 (${DayFormatter(dayFormatter.format(date))}) ${
                    DayDividerFormatter(
                        dayDividerFormatter.format(date)
                    )
                    } hh시 mm분 "
                )
            binding.tvTime.append(formatter.format(date))
        }

        exchangeDetailViewModel.remainedList.observe(this) {
            val date = Date()
            val currentTime = Calendar.getInstance().timeInMillis / 1000L
            date.time = (it.pickUpTime - currentTime) * 1000L
            Log.d("dkslfj", it.pickUpTime.toString())
            Log.d("dkslfj2", currentTime.toString())
            val pickUpDateFormatter = SimpleDateFormat("교환 완료 mm분 남음")

            binding.tvExchangeComplete.append(pickUpDateFormatter.format(date))
        }
    }

    private fun completeExchange() {
        binding.ivCompleteExchange.setOnClickListener {
            binding.ivCompleteExchange.background =
                getDrawable(R.drawable.shape_gray_fills_4_rectangle)

            binding.tvExchangeComplete.text = "교환 완료"
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

    private fun DayFormatter(day: String): String {
        return when (day) {
            "Mon" -> "월"
            "Tue" -> "화"
            "Wed" -> "수"
            "Thu" -> "목"
            "Fri" -> "금"
            "Sat" -> "토"
            else -> "일"
        }
    }

    private fun DayDividerFormatter(divider: String): String {
        return when (divider) {
            "AM" -> "오전"
            else -> "오후"
        }
    }
}
