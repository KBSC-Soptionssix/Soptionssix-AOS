package com.kbsc.soptionssix.exchangetab

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kbsc.soptionssix.exchange.ExchangeDetailActivity
import com.kbsc.soptionssix.store.StoreDetailActivity
import com.kbsc.soptionssix.write.ReadReviewActivity
import com.kbsc.soptionssix.write.WriteReviewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExchangeFragment : Fragment() {
    private val exchangeTabViewModel: ExchangeTabViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ExchangeTabScreen(
                    exchangeTabViewModel = exchangeTabViewModel,
                    goExchangeDetail = ::goExchangeDetail,
                    goWriteReview = ::goWriteReview,
                    goReadReview = ::goReadReview,
                    goStoreDetail = ::goStoreDetail
                )
            }
        }
    }

    private fun goStoreDetail(storeId: String) {
        val intent = Intent(requireActivity(), StoreDetailActivity::class.java)
        intent.putExtra("storeId", storeId)
        startActivity(intent)
    }

    private fun goExchangeDetail(receiptId: String) {
        val intent = Intent(requireActivity(), ExchangeDetailActivity::class.java)
        intent.putExtra("receiptId", receiptId)
        startActivity(intent)
    }

    private fun goWriteReview(
        reviewId: String?,
        storeName: String,
        foodName: String,
        region: String
    ) {
        val intent = Intent(requireActivity(), WriteReviewActivity::class.java)
        intent.apply {
            putExtra("reviewId", reviewId)
            putExtra("storeName", storeName)
            putExtra("foodName", foodName)
            putExtra("region", region)
        }
        startActivity(intent)
    }

    private fun goReadReview(reviewId: String?) {
        val intent = Intent(requireActivity(), ReadReviewActivity::class.java)
        intent.putExtra("reviewId", reviewId)
        startActivity(intent)
    }
}
