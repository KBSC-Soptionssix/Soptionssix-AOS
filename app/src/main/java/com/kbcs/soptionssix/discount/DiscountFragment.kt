package com.kbcs.soptionssix.discount

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kbcs.soptionssix.buy.BuyFoodActivity
import com.kbcs.soptionssix.databinding.FragmentDiscountBinding
import com.kbcs.soptionssix.store.DiscountProductAdapter
import com.kbcs.soptionssix.store.StoreDetailActivity
import com.kbsc.data.dto.ProductDto
import com.kbsc.data.dto.StoreDto
import com.kbsc.data.dto.StorePreviewDto

class DiscountFragment : Fragment() {
    private var _binding: FragmentDiscountBinding? = null
    private val binding get() = requireNotNull(_binding)
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
    }

    private fun discountStoreAdapter() {
        val detailList = listOf(
            StoreDto(
                "1",
                "https://mblogthumb-phinf.pstatic.net/MjAyMTAxMjRfMjQ0/MDAxNjExNDQ3NDAyOTA0.VkuU0VquRRykzvq_185PZKNnP0lldIsH8oZphIlhGIEg.ybyJQWFmjqIWlu3hTgYn91kOfnPatHmdNcd_BVBpgscg.JPEG.shelly814/IMG_9859.jpg?type=w800",
                "멕시칸인더보울",
                "샐러드, 챌린지",
                "안녕하세요 멕시칸 타코를 전물으로 하고 있습니다."
            ),
            StoreDto(
                "2",
                "https://mblogthumb-phinf.pstatic.net/MjAyMTAxMjRfMjQ0/MDAxNjExNDQ3NDAyOTA0.VkuU0VquRRykzvq_185PZKNnP0lldIsH8oZphIlhGIEg.ybyJQWFmjqIWlu3hTgYn91kOfnPatHmdNcd_BVBpgscg.JPEG.shelly814/IMG_9859.jpg?type=w800",
                "멕시칸인더보울",
                "샐러드, 챌린지",
                "안녕하세요 멕시칸 타코를 전물으로 하고 있습니다."
            ),
            StoreDto(
                "3",
                "https://mblogthumb-phinf.pstatic.net/MjAyMTAxMjRfMjQ0/MDAxNjExNDQ3NDAyOTA0.VkuU0VquRRykzvq_185PZKNnP0lldIsH8oZphIlhGIEg.ybyJQWFmjqIWlu3hTgYn91kOfnPatHmdNcd_BVBpgscg.JPEG.shelly814/IMG_9859.jpg?type=w800",
                "멕시칸인더보울",
                "샐러드, 챌린지",
                "안녕하세요 멕시칸 타코를 전물으로 하고 있습니다."
            ),
            StoreDto(
                "4",
                "https://mblogthumb-phinf.pstatic.net/MjAyMTAxMjRfMjQ0/MDAxNjExNDQ3NDAyOTA0.VkuU0VquRRykzvq_185PZKNnP0lldIsH8oZphIlhGIEg.ybyJQWFmjqIWlu3hTgYn91kOfnPatHmdNcd_BVBpgscg.JPEG.shelly814/IMG_9859.jpg?type=w800",
                "멕시칸인더보울",
                "샐러드, 챌린지",
                "안녕하세요 멕시칸 타코를 전물으로 하고 있습니다."
            )
        )
        discountStoreAdapter = DiscountStoreAdapter {
            val intent = Intent(requireContext(), StoreDetailActivity::class.java)
            intent.putExtra("storeId", it.id)
            startActivity(intent)
        }
        binding.rvStore.adapter = discountStoreAdapter
        discountStoreAdapter.submitList(detailList)
    }

    private fun discountAdapter() {
        val detailList = listOf(
            ProductDto(
                "1",
                StorePreviewDto(),
                "https://mblogthumb-phinf.pstatic.net/MjAyMTAxMjRfMjQ0/MDAxNjExNDQ3NDAyOTA0.VkuU0VquRRykzvq_185PZKNnP0lldIsH8oZphIlhGIEg.ybyJQWFmjqIWlu3hTgYn91kOfnPatHmdNcd_BVBpgscg.JPEG.shelly814/IMG_9859.jpg?type=w800",
                "핫페퍼 아보카도 에그 후무스 3p",
                3,
                12000,
                10,
                1,
                2
            ),
            ProductDto(
                "2",
                StorePreviewDto(),
                "https://mblogthumb-phinf.pstatic.net/MjAyMTAxMjRfMjQ0/MDAxNjExNDQ3NDAyOTA0.VkuU0VquRRykzvq_185PZKNnP0lldIsH8oZphIlhGIEg.ybyJQWFmjqIWlu3hTgYn91kOfnPatHmdNcd_BVBpgscg.JPEG.shelly814/IMG_9859.jpg?type=w800",
                "상큼 라임 비프 타코 3p",
                3,
                9000,
                50,
                0,
                2
            ),
            ProductDto(
                "3",
                StorePreviewDto(),
                "https://mblogthumb-phinf.pstatic.net/MjAyMTAxMjRfMjQ0/MDAxNjExNDQ3NDAyOTA0.VkuU0VquRRykzvq_185PZKNnP0lldIsH8oZphIlhGIEg.ybyJQWFmjqIWlu3hTgYn91kOfnPatHmdNcd_BVBpgscg.JPEG.shelly814/IMG_9859.jpg?type=w800",
                "수제 샤워소스 2p",
                3,
                6000,
                30,
                1,
                0
            ),
            ProductDto(
                "4",
                StorePreviewDto(),
                "https://mblogthumb-phinf.pstatic.net/MjAyMTAxMjRfMjQ0/MDAxNjExNDQ3NDAyOTA0.VkuU0VquRRykzvq_185PZKNnP0lldIsH8oZphIlhGIEg.ybyJQWFmjqIWlu3hTgYn91kOfnPatHmdNcd_BVBpgscg.JPEG.shelly814/IMG_9859.jpg?type=w800",
                "체다 듬뿍 핫치킨 부리또 1p",
                3,
                10000,
                20,
                2,
                0
            )
        )
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

        discountAdapter =
            DiscountProductAdapter(clickBuy = goBuy, clickGive = goGive, clickWait = goWait)

        binding.rvProduct.adapter = discountAdapter
        discountAdapter.submitList(detailList)
    }

    private fun waitingAdapter() {
        val detailList = listOf(
            ProductDto(
                "1",
                StorePreviewDto(),
                "https://mblogthumb-phinf.pstatic.net/MjAyMTAxMjRfMjQ0/MDAxNjExNDQ3NDAyOTA0.VkuU0VquRRykzvq_185PZKNnP0lldIsH8oZphIlhGIEg.ybyJQWFmjqIWlu3hTgYn91kOfnPatHmdNcd_BVBpgscg.JPEG.shelly814/IMG_9859.jpg?type=w800",
                "핫페퍼 아보카도 에그 후무스 3p",
                3,
                12000,
                10,
                1,
                2
            ),
            ProductDto(
                "2",
                StorePreviewDto(),
                "https://mblogthumb-phinf.pstatic.net/MjAyMTAxMjRfMjQ0/MDAxNjExNDQ3NDAyOTA0.VkuU0VquRRykzvq_185PZKNnP0lldIsH8oZphIlhGIEg.ybyJQWFmjqIWlu3hTgYn91kOfnPatHmdNcd_BVBpgscg.JPEG.shelly814/IMG_9859.jpg?type=w800",
                "상큼 라임 비프 타코 3p",
                3,
                9000,
                50,
                0,
                2
            ),
            ProductDto(
                "3",
                StorePreviewDto(),
                "https://mblogthumb-phinf.pstatic.net/MjAyMTAxMjRfMjQ0/MDAxNjExNDQ3NDAyOTA0.VkuU0VquRRykzvq_185PZKNnP0lldIsH8oZphIlhGIEg.ybyJQWFmjqIWlu3hTgYn91kOfnPatHmdNcd_BVBpgscg.JPEG.shelly814/IMG_9859.jpg?type=w800",
                "수제 샤워소스 2p",
                3,
                6000,
                30,
                1,
                0
            ),
            ProductDto(
                "4",
                StorePreviewDto(),
                "https://mblogthumb-phinf.pstatic.net/MjAyMTAxMjRfMjQ0/MDAxNjExNDQ3NDAyOTA0.VkuU0VquRRykzvq_185PZKNnP0lldIsH8oZphIlhGIEg.ybyJQWFmjqIWlu3hTgYn91kOfnPatHmdNcd_BVBpgscg.JPEG.shelly814/IMG_9859.jpg?type=w800",
                "체다 듬뿍 핫치킨 부리또 1p",
                3,
                10000,
                20,
                2,
                0
            )
        )
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
        waitingProductAdapter.submitList(detailList)
    }
}
