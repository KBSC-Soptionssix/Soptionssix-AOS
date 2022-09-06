package com.kbcs.soptionssix.buy

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.databinding.ActivityBuyBinding
import com.kbcs.soptionssix.navermap.NaverMapFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class BuyFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuyBinding
    private val buyViewModel: BuyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_buy)
        val bundle = Bundle()
        val naverMapFragment = NaverMapFragment()

        buyViewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach { buyUiState ->
                naverMapFragment.arguments = bundle.apply {
                    putDouble("latitude", buyUiState.mapX)
                    putDouble("longitude", buyUiState.mapY)
                }
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fcv_buy_naver_map, naverMapFragment)
                    .commit()
            }
            .launchIn(lifecycleScope)

        with(binding) {
            buyTopScreenCv.setContent { BuyScreenTop(buyViewModel) }
            buyBottomScreenCv.setContent { BuyScreenBottom(buyViewModel) }
        }
    }
}
