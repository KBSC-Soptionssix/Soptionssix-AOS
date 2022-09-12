package com.kbcs.soptionssix.review

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kbcs.soptionssix.MainActivity
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.databinding.FragmentReviewBinding
import com.kbcs.soptionssix.store.StoreDetailActivity

class ReviewFragment : Fragment() {
    private var _binding: FragmentReviewBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel: ReviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.reviewComposeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ReviewScreen(
                    reviewViewModel = viewModel,
                    changeScreen = ::changeScreen,
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

    private fun changeScreen(screenId: Int) {
        if (screenId == 0) (activity as MainActivity).changeScreen(R.id.menu_product)
        else (activity as MainActivity).changeScreen(R.id.menu_exchange)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
