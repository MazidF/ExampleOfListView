package com.example.exampleoflistview

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

@BindingAdapter("app:image") // or image
fun setImage(imageView: ImageView, movie: Movie) {
    val bitmap = movie.bitmap
    if (movie.imageUrl == null) {
        imageView.setImageResource(R.drawable.movie)
    } else if (bitmap == null) {
        Glide.with(imageView.context)
            .asBitmap()
            .load(movie.imageUrl)
            .placeholder(R.drawable.ic_loading)
/*            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ) = false

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    movie.bitmap = resource
                    imageView.setImageBitmap(resource)
                    return true
                }

            })*/
            .into(imageView)
    } else {
        imageView.setImageBitmap(bitmap)
    }
}

fun Int.format(length: Int = 2): String {
    return String.format("%${length}0d", this)
}