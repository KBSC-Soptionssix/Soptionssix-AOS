package com.kbcs.soptionssix.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("setImage")
    fun ImageView.setImage(url: String) {
        load(url)
    }
}
