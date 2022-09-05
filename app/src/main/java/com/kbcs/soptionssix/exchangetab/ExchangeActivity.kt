package com.kbcs.soptionssix.exchangetab

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class ExchangeActivity : AppCompatActivity() {
    private val exchangeTabViewModel: ExchangeTabViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExchangeTabScreen(exchangeTabViewModel = exchangeTabViewModel)
        }
    }
}
