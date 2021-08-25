package com.mytran.myapplication.utils

import android.widget.ImageView
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey

fun ImageView.loadImage(url: String, scaleSize: Int) =
    com.bumptech.glide.Glide.with(context).load(url).apply(
        RequestOptions()
        .fitCenter()
        .override(scaleSize)
        .priority(Priority.IMMEDIATE)
        .format(DecodeFormat.PREFER_RGB_565)
        .signature(ObjectKey(System.currentTimeMillis() / (24 * 60 * 60 * 1000)))
        .diskCacheStrategy(DiskCacheStrategy.ALL))