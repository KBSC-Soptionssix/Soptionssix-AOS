package com.kbcs.soptionssix.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("setImage")
    fun ImageView.setImage(url: String) {
        load(url)
//        Glide.with(context)
//            .load(url)
//            .into(this)
    }

//    @JvmStatic
//    @BindingAdapter("setCircleImage")
//    fun setCircleImage(imageview: ImageView, url: String?) {
//        Glide.with(imageview.context)
//            .load(url)
//            .circleCrop()
//            .into(imageview)
//    }
}
