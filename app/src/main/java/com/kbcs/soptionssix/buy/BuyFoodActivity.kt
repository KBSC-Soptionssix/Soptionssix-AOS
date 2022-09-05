package com.kbcs.soptionssix.buy

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.databinding.ActivityBuyBinding

class BuyFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuyBinding
    private val buyViewModel: BuyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_buy)

        with(binding) {
            buyTopScreenCv.setContent { BuyScreenTop(buyViewModel) }
            buyBottomScreenCv.setContent { BuyScreenBottom() }
        }
    }
}
