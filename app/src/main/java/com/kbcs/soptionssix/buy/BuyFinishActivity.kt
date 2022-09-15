package com.kbcs.soptionssix.buy

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import com.kbcs.soptionssix.MainActivity
import com.kbcs.soptionssix.util.theme.PretendardTypography

class BuyFinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(typography = PretendardTypography) {
                BuyFinishScreen(::goMain)
            }
        }
    }

    private fun goMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("goExchangeTab", true)
        startActivity(intent)
        finishAffinity()
    }
}
