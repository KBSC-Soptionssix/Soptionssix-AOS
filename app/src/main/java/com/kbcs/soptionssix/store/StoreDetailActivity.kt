package com.kbcs.soptionssix.store

import android.content.*
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.buy.BuyFoodActivity
import com.kbcs.soptionssix.databinding.ActivityStoreDetailBinding
import com.kbcs.soptionssix.navermap.NaverMapFragment
import com.kbsc.data.dto.ProductDto
import com.kbsc.data.dto.StorePreviewDto

class StoreDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStoreDetailBinding
    private lateinit var discountProductAdapter: DiscountProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_store_detail)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<NaverMapFragment>(R.id.fcv_naver_map)
        }

        discountDetailAdapter()
        backButton()
        copyPhoneNumber()
        copyRoadClipData()
        copyAddressClipData()
    }

    private fun discountDetailAdapter() {
        val detailList = listOf(
            ProductDto("1", StorePreviewDto(), null, "핫페퍼 아보카도 에그 후무스 3p", 3, 12000, 10, 1, 2),
            ProductDto("2", StorePreviewDto(), null, "상큼 라임 비프 타코 3p", 3, 9000, 50, 0, 2),
            ProductDto("3", StorePreviewDto(), null, "수제 샤워소스 2p", 3, 6000, 30, 1, 0),
            ProductDto("4", StorePreviewDto(), null, "체다 듬뿍 핫치킨 부리또 1p", 3, 10000, 20, 2, 0)
        )
        val goBuy: (ProductDto) -> Unit = {
            val intent = Intent(this, BuyFoodActivity::class.java)
            intent.putExtra("productId", it.id)
            startActivity(intent)
        }
        val goGive: (ProductDto) -> Unit = {
            val intent = Intent(this, BuyFoodActivity::class.java)
            intent.putExtra("productId", it.id)
            startActivity(intent)
        }
        val goWait: (ProductDto) -> Unit = {
            Toast.makeText(this, "기부 대기 되셨습니다.", Toast.LENGTH_SHORT).show()
        }

        discountProductAdapter =
            DiscountProductAdapter(clickBuy = goBuy, clickGive = goGive, clickWait = goWait)

        binding.rvProduct.adapter = discountProductAdapter
        discountProductAdapter.submitList(detailList)
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
