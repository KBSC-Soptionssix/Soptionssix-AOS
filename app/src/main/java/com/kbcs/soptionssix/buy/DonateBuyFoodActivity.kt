package com.kbcs.soptionssix.buy

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.databinding.ActivityDonateBuyFoodBinding
import com.kbcs.soptionssix.navermap.NaverMapFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
        lifecycleScope.launch {
            viewModel.setIsDonate()
            val productId = intent.getStringExtra("productId") ?: ""
            viewModel.fetchBuyContent(productId)
        }
        val bundle = Bundle()
        val naverMapFragment = NaverMapFragment()

        viewModel.uiState
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
            donateBuyTopScreenCv.setContent {
                BuyScreenTop(
                    buyViewModel = viewModel,
                    finish = ::finish
                )
            }
            donateBuyBottomScreenCv.setContent {
                DonateBuyScreenBottom(
                    buyViewModel = viewModel,
                    finish = ::finish
                )
            }
        }
    }
}
