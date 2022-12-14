package com.kbsc.soptionssix.loading

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.lifecycle.lifecycleScope
import com.kbsc.soptionssix.buy.BuyFinishActivity
import com.kbsc.soptionssix.util.theme.PretendardTypography
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(typography = PretendardTypography) {
                LoadingScreen()
            }
        }
        lifecycleScope.launch(Dispatchers.Main) {
            delay(600)
            Intent(
                this@LoadingActivity,
                BuyFinishActivity::class.java
            ).also { intent ->
                startActivity(intent)
            }
            finish()
        }
    }
}
