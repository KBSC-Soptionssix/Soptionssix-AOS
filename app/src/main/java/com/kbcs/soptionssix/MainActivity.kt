package com.kbcs.soptionssix

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kbcs.soptionssix.databinding.ActivityMainBinding
import com.kbcs.soptionssix.discount.DiscountFragment
import com.kbcs.soptionssix.exchangetab.ExchangeFragment
import com.kbcs.soptionssix.review.ReviewFragment
import java.time.LocalDateTime
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initBottomNavi()
        timeFunc()
    }

    private fun timeFunc() {
        val di = LocalDateTime.now()
        Log.d("hello", di.minute.toString())
        Log.d("hello", di.hour.toString())
    }

    fun changeScreen(menuId: Int) {
        binding.bottomNavigation.selectedItemId = menuId
    }

    private fun initBottomNavi() {
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, ReviewFragment())
            .commit()

        binding.bottomNavigation.selectedItemId = R.id.menu_comment

        binding.bottomNavigation.setOnItemSelectedListener {
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
    }
}
