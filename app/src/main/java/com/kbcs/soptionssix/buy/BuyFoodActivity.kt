package com.kbcs.soptionssix.buy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.databinding.ActivityBuyBinding

class BuyFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_buy)

        with(binding) {
            buyTopScreenCv.setContent { BuyScreenTop() }
            buyBottomScreenCv.setContent { BuyScreenBottom() }
        }
    }
}
