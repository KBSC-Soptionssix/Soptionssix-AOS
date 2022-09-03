package com.kbcs.soptionssix.exchange

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.databinding.ActivityExchangeDetailBinding
import com.kbcs.soptionssix.navermap.NaverMapFragment

class ExchangeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExchangeDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exchange_detail)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<NaverMapFragment>(R.id.fcv_naver_map)
        }
    }
}
