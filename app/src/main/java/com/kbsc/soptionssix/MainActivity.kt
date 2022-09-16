package com.kbsc.soptionssix

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kbsc.soptionssix.databinding.ActivityMainBinding
import com.kbsc.soptionssix.discount.DiscountFragment
import com.kbsc.soptionssix.exchangetab.ExchangeFragment
import com.kbsc.soptionssix.review.ReviewFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initBottomNavi()
        val isGoExchangeTab = intent.getBooleanExtra("goExchangeTab", false)
        if (isGoExchangeTab) changeScreen(R.id.menu_exchange)
    }

    fun changeScreen(menuId: Int) {
        binding.bottomNavigation.selectedItemId = menuId
    }

    private fun initBottomNavi() {
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, ReviewFragment())
            .commit()

        binding.bottomNavigation.selectedItemId = R.id.menu_comment

        binding.bottomNavigation.apply {
            setOnItemSelectedListener {
                val transaction = supportFragmentManager.beginTransaction()
                when (it.itemId) {
                    R.id.menu_comment -> transaction.replace(
                        R.id.fragmentContainerView,
                        ReviewFragment()
                    )
                    R.id.menu_product -> transaction.replace(
                        R.id.fragmentContainerView,
                        DiscountFragment()
                    )
                    R.id.menu_exchange -> transaction.replace(
                        R.id.fragmentContainerView,
                        ExchangeFragment()
                    )
                    else -> error("item id :${it.itemId}) is cannot be selected")
                }
                transaction.commit()
                true
            }

            setOnItemReselectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menu_comment -> Unit
                    R.id.menu_product -> Unit
                    R.id.menu_exchange -> Unit
                }
            }
        }
    }
}
