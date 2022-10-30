package co.innovatespace.ui

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.setImage(url: String) = Glide.with(this.context).load(
    url.ifEmpty { "https://heuft.com/upload/image/400x267/no_image_placeholder.png" }
).transition(DrawableTransitionOptions.withCrossFade()).into(this)