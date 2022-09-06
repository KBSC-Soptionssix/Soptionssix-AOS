package com.kbcs.soptionssix.buy

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.databinding.ActivityBuyBinding
import com.kbcs.soptionssix.navermap.NaverMapFragment

class BuyFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuyBinding
    private val buyViewModel: BuyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_buy)

        with(binding) {
            buyTopScreenCv.setContent { BuyScreenTop(buyViewModel) }
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<NaverMapFragment>(R.id.fcv_buy_naver_map)
            }
            buyBottomScreenCv.setContent { BuyScreenBottom(buyViewModel) }
        }
    }
}
