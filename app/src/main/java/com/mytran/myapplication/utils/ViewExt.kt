package com.mytran.myapplication.utils

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.signature.ObjectKey
import com.mytran.myapplication.R

fun ImageView.loadImage(url: String, scaleSize: Int, loadingResult: (result: Boolean) -> Unit) =
    Glide.with(context).load(url).apply(RequestOptions()
        .fitCenter()
        .override(scaleSize)
        .priority(Priority.IMMEDIATE)
        .format(DecodeFormat.PREFER_RGB_565)
        .signature(ObjectKey(System.currentTimeMillis() / (24 * 60 * 60 * 1000)))
        .diskCacheStrategy(DiskCacheStrategy.ALL)).listener(object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            loadingResult.invoke(false)
            Log.d("Glide", "loadImage url= $url false ${e?.message}")
            return false
        }

        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            loadingResult.invoke(true)
            Log.d("Glide", "loadImage url= $url true")
            return false
        }

    }).error(R.drawable.image_default_rectangle).into(this)