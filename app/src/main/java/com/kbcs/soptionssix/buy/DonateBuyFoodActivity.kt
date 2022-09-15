package com.kbcs.soptionssix.buy

import android.content.ClipData
import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.databinding.ActivityDonateBuyFoodBinding
import com.kbcs.soptionssix.loading.LoadingActivity
import com.kbcs.soptionssix.navermap.NaverMapFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DonateBuyFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDonateBuyFoodBinding
    private val viewModel: BuyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityDonateBuyFoodBinding?>(
            this,
            R.layout.activity_donate_buy_food
        ).apply {
            vm = viewModel
            lifecycleOwner = this@DonateBuyFoodActivity
        }
        copyRoadClipData()
        copyAddressClipData()
        collectUiState()
        with(binding) {
            donateBuyTopScreenCv.setContent {
                BuyScreenTop(
                    buyViewModel = viewModel,
                    finish = ::finish
                )
            }
            donateBuyBottomScreenCv.setContent {
                DonateBuyScreenBottom(
                    buyViewModel = viewModel,
                    finish = ::goLoading
                )
            }
        }
    }

    private fun collectUiState() {
        val bundle = Bundle()
        val naverMapFragment = NaverMapFragment()

        lifecycleScope.launch {
            val productId = intent.getStringExtra("productId") ?: ""
            viewModel.fetchBuyContent(productId)
            viewModel.setIsDonate()
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { buyUiState ->
                    naverMapFragment.arguments = bundle.apply {
                        putDouble("latitude", buyUiState.mapX)
                        putDouble("longitude", buyUiState.mapY)
                    }
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fcv_buy_naver_map, naverMapFragment)
                        .commit()
                }
            }
        }
    }

    private fun goLoading() {
        Intent(this, LoadingActivity::class.java).also { intent ->
            startActivity(intent)
            finish()
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
