package co.innovatespace.utility

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.setImage(url: String) = Glide.with(this.context).load(
    url.ifEmpty { null }
).transition(DrawableTransitionOptions.withCrossFade()).into(this)