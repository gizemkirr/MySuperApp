package com.example.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun loadImage(view : ImageView, image : Int){
    Glide.with(view.context)
        .load(image)
        .into(view)
}

@BindingAdapter("imageURL")
fun imageURL(view: ImageView, imageURL: String){
    Glide.with(view.context)
        .load(imageURL)
        .into(view)
}

fun View.safeButtonClickListener(action: (v: View) -> Unit) {
    setOnClickListener { v ->
        isClickable = false
        action(v)
        postDelayed({ isClickable = true }, 1500)
    }
}