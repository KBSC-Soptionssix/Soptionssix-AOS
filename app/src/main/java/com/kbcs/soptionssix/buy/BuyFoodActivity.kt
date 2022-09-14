package com.kbcs.soptionssix.buy

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.databinding.ActivityBuyBinding
import com.kbcs.soptionssix.navermap.NaverMapFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BuyFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuyBinding
    private val buyViewModel: BuyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_buy)

        val bundle = Bundle()
        val naverMapFragment = NaverMapFragment()

        lifecycleScope.launch {
            val productId = intent.getStringExtra("productId") ?: ""
            buyViewModel.fetchBuyContent(productId)

            repeatOnLifecycle(Lifecycle.State.STARTED) {
                buyViewModel.uiState.collect { buyUiState ->
                    naverMapFragment.arguments = bundle.apply {
                        putDouble("latitude", buyUiState.mapX)
                        putDouble("longitude", buyUiState.mapY)
                    }
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fcv_buy_naver_map, naverMapFragment)
                        .commit()

                    binding.tvStoreName.text = buyUiState.storeName
                    binding.tvAddressDetail.text = buyUiState.address
                    binding.tvRoadDetail.text = buyUiState.loadAddress
                }
            }
        }

        with(binding) {
            buyTopScreenCv.setContent {
                BuyScreenTop(
                    buyViewModel = buyViewModel,
                    finish = ::finish
                )
            }
            buyBottomScreenCv.setContent {
                BuyScreenBottom(
                    buyViewModel = buyViewModel,
                    finish = ::finish
                )
            }
            ivTimeEmo.setOnClickListener {
                ivTimeEmo.isSelected = !ivTimeEmo.isSelected
                ivTimeBox.isSelected = !ivTimeBox.isSelected
                buyViewModel.setIsVisitedStore()
            }
        }
    }
}
