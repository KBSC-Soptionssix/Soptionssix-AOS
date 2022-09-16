package com.kbsc.soptionssix.write

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.core.view.WindowCompat
import com.kbsc.soptionssix.MainActivity
import com.kbsc.soptionssix.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteReviewActivity : AppCompatActivity() {
    private val writeReviewViewModel: WriteReviewViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val receiptId = intent.getStringExtra("reviewId") ?: ""
        val storeName = intent.getStringExtra("storeName") ?: ""
        val foodName = intent.getStringExtra("foodName") ?: ""
        val region = intent.getStringExtra("region") ?: ""
        writeReviewViewModel.fetchWriteReviewContent(
            receiptId = receiptId,
            storeName = storeName,
            foodName = foodName,
            region = region
        )
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            WriteReviewScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.view_background))
                    .windowInsetsPadding(
                        WindowInsets.systemBars.only(
                            WindowInsetsSides.Vertical
                        )
                    ),
                title = stringResource(R.string.writeReview),
                buttonText = stringResource(R.string.writePostReview),
                writeReviewViewModel = writeReviewViewModel,
                buttonEvent = writeReviewViewModel::postReview,
                finish = ::goMain
            )
        }
    }

    private fun goMain() {
        Toast.makeText(this, "후기 작성이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("goExchangeTab", true)
        startActivity(intent)
        finishAffinity()
    }
}
