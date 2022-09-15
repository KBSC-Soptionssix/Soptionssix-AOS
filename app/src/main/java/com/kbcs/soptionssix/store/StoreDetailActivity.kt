package com.kbcs.soptionssix.store

import android.content.ClipData
import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import coil.load
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.buy.BuyFoodActivity
import com.kbcs.soptionssix.buy.DonateBuyFoodActivity
import com.kbcs.soptionssix.databinding.ActivityStoreDetailBinding
import com.kbcs.soptionssix.navermap.NaverMapFragment
import com.kbsc.data.dto.DiscountStoreDetailDto
import com.kbsc.data.dto.ProductDto
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StoreDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStoreDetailBinding
    private lateinit var discountProductAdapter: DiscountProductAdapter
    private val storeDetailViewModel: StoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_store_detail)
        val storeId = intent.getStringExtra("storeId") ?: ""

        lifecycleScope.launch {
            storeDetailViewModel.fetchStoreDetailList(storeId)
        }

        discountDetailAdapter()
        storeObserver()
        backButton()
        copyPhoneNumber()
        copyRoadClipData()
        copyAddressClipData()
    }

    private fun discountDetailAdapter() {
        val goBuy: (ProductDto) -> Unit = {
            val intent = Intent(this, BuyFoodActivity::class.java)
            intent.putExtra("productId", it.id)
            startActivity(intent)
        }
        val goGive: (ProductDto) -> Unit = {
            val intent = Intent(this, DonateBuyFoodActivity::class.java)
            intent.putExtra("productId", it.id)
            startActivity(intent)
        }
        val goWait: (ProductDto) -> Unit = {
            Toast.makeText(this, "기부 대기 되셨습니다.", Toast.LENGTH_SHORT).show()
        }

        discountProductAdapter =
            DiscountProductAdapter(clickBuy = goBuy, clickGive = goGive, clickWait = goWait)

        binding.rvProduct.adapter = discountProductAdapter
    }

    private fun storeObserver() {
        storeDetailViewModel.storeInfo.observe(this) {
            binding.storeDetailViewModel = it
            if (it.hasChallenge) {
                binding.tvTag2.visibility = View.VISIBLE
                binding.tvTag2.text = "용기내챌린지 환영"
            } else {
                binding.tvTag2.visibility = View.GONE
            }

            if (it.photo != null) {
                binding.ivShop.load(it.photo)
            }

            timeSetting(it)

            val bundle = Bundle()
            val naverMapFragment = NaverMapFragment()

            naverMapFragment.arguments = bundle.apply {
                putDouble("latitude", it.mapX.toDouble())
                putDouble("longitude", it.mapY.toDouble())
            }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fcv_naver_map, naverMapFragment)
                .commit()

            if (it != null) discountProductAdapter.submitList(it.products)
        }
    }

    private fun timeSetting(it: DiscountStoreDetailDto?) {
        val breakStartHour = it?.breakStartTime?.div(60)
        val breakEndHour = it?.breakEndTime?.rem(60)
        if (breakStartHour != null && breakEndHour != null) {
            when {
                breakStartHour > 12 -> {
                    binding.tvBreakDetail.text = "오후 $breakStartHour" + "시 ~ 오후 $breakEndHour" + "시"
                }
                breakEndHour > 12 -> {
                    binding.tvBreakDetail.text = "오전 $breakStartHour" + "시 ~ 오후 $breakEndHour" + "시"
                }
                else -> {
                    binding.tvBreakDetail.text = "오전 $breakStartHour" + "시 ~ 오전 $breakEndHour" + "시"
                }
            }
        }

        val startHour = it?.startTime?.div(60)
        val endHour = it?.endTime?.rem(60)

        if (startHour != null && endHour != null) {
            when {
                startHour > 12 -> {
                    binding.tvBreakDetail.text = "오후 $startHour" + "시 ~ 오후 $endHour" + "시"
                }
                endHour > 12 -> {
                    binding.tvBreakDetail.text = "오전 $startHour" + "시 ~ 오후 $endHour" + "시"
                }
                else -> {
                    binding.tvBreakDetail.text = "오전 $startHour" + "시 ~ 오전 $endHour" + "시"
                }
            }
        }
    }

    private fun backButton() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun copyPhoneNumber() {
        binding.tvCopy.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("copyData", binding.tvTelephone.text)
            // 클립보드에 배치
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "복사되었습니다.", Toast.LENGTH_SHORT).show()
            checkPaste(clipboard)
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
            !(clipManager.primaryClipDescription?.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))!! -> false
            else -> true
        }
    }
}
