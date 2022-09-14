package com.kbcs.soptionssix.write

import android.os.Bundle
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
import com.kbcs.soptionssix.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteReviewActivity : AppCompatActivity() {
    private val writeReviewViewModel: WriteReviewViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val receiptId = intent.getStringExtra("reviewId") ?: ""
        val storeName = intent.getStringExtra("storeName") ?: ""
        val foodName = intent.getStringExtra("foodName") ?: ""
        val address = intent.getStringExtra("address") ?: ""
        writeReviewViewModel.fetchWriteReviewContent(
            receiptId = receiptId,
            storeName = storeName,
            foodName = foodName,
            address = address
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
                finish = ::finish
            )
        }
    }
}
