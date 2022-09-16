package com.kbsc.soptionssix.discount

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kbsc.data.dto.ProductDto
import com.kbsc.soptionssix.buy.BuyFoodActivity
import com.kbsc.soptionssix.buy.DonateBuyFoodActivity
import com.kbsc.soptionssix.databinding.FragmentDiscountBinding
import com.kbsc.soptionssix.store.DiscountProductAdapter
import com.kbsc.soptionssix.store.StoreDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscountFragment : Fragment() {
    private var _binding: FragmentDiscountBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val discountViewModel: DiscountViewModel by viewModels()
    private lateinit var discountAdapter: DiscountProductAdapter
    private lateinit var waitingProductAdapter: DiscountProductAdapter
    private lateinit var discountStoreAdapter: DiscountStoreAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        discountStoreAdapter()
        discountAdapter()
        waitingAdapter()
        discountObserver()
    }

    private fun discountStoreAdapter() {
        discountStoreAdapter = DiscountStoreAdapter {
            val intent = Intent(requireContext(), StoreDetailActivity::class.java)
            intent.putExtra("storeId", it.id)
            startActivity(intent)
        }
        binding.rvStore.adapter = discountStoreAdapter
    }

    private fun discountAdapter() {
        val goBuy: (ProductDto) -> Unit = {
            val intent = Intent(requireContext(), BuyFoodActivity::class.java)
            intent.putExtra("productId", it.id)
            startActivity(intent)
        }
        val goGive: (ProductDto) -> Unit = {
            val intent = Intent(requireContext(), DonateBuyFoodActivity::class.java)
            intent.putExtra("productId", it.id)
            startActivity(intent)
        }
        val goWait: (ProductDto) -> Unit = {
            Toast.makeText(requireContext(), "기부 대기되었습니다! 완료되면 알려드릴게요.", Toast.LENGTH_SHORT).show()
        }

        discountAdapter =
            DiscountProductAdapter(clickBuy = goBuy, clickGive = goGive, clickWait = goWait)

        binding.rvProduct.adapter = discountAdapter
    }

    private fun waitingAdapter() {
        val goBuy: (ProductDto) -> Unit = {
            val intent = Intent(requireContext(), BuyFoodActivity::class.java)
            intent.putExtra("productId", it.id)
            startActivity(intent)
        }
        val goGive: (ProductDto) -> Unit = {
            val intent = Intent(requireContext(), BuyFoodActivity::class.java)
            intent.putExtra("productId", it.id)
            startActivity(intent)
        }
        val goWait: (ProductDto) -> Unit = {
            Toast.makeText(requireContext(), "기부 대기 되셨습니다.", Toast.LENGTH_SHORT).show()
        }

        waitingProductAdapter =
            DiscountProductAdapter(clickBuy = goBuy, clickGive = goGive, clickWait = goWait)

        binding.rvWaitingProduct.adapter = waitingProductAdapter
    }

    private fun discountObserver() {
        discountViewModel.storeList.observe(viewLifecycleOwner) {
            if (it != null) discountStoreAdapter.submitList(it)
        }
        discountViewModel.productList.observe(viewLifecycleOwner) {
            if (it != null) discountAdapter.submitList(it)
        }
        discountViewModel.waitingList.observe(viewLifecycleOwner) {
            if (it != null) waitingProductAdapter.submitList(it)
        }
    }
}
