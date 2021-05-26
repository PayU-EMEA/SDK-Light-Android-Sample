package com.payu.android.front.sdk.demo.ui.base.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout

private const val MIN_ALPHA = 0.35f
private const val MAX_ALPHA = 1.0f

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("error")
    fun setError(view: TextInputLayout, resId: Int?) {
        view.error = resId?.let { view.resources.getString(it) }
    }

    @JvmStatic
    @BindingAdapter("visibleIf")
    fun changeViewVisibility(view: View, bool: Boolean) {
        view.visibility = if (bool) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("invisibleIf")
    fun changeViewInvisibility(view: View, bool: Boolean) {
        view.visibility = if (bool) View.INVISIBLE else View.VISIBLE
    }

    @JvmStatic
    @BindingAdapter("enableIf")
    fun changeViewIsEnable(view: View, bool: Boolean) {
        view.isEnabled = bool
    }

    @JvmStatic
    @BindingAdapter(value = ["enabledIf", "alpha"], requireAll = false)
    fun enabledIf(view: View, bool: Boolean?, alpha: Float?) {
        val newAlpha = alpha ?: MIN_ALPHA

        if (bool == null || !bool) {
            view.isEnabled = false
            newAlpha.also { view.animate().alpha(it) }
        } else {
            view.animate().alpha(MAX_ALPHA).withEndAction { view.isEnabled = true }
        }
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bitmapSource(view: ImageView, uri: String?) {
        if (!uri.isNullOrEmpty()) {
            Glide.with(view.context)
                .load(uri)
                .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageResource(imageView: ImageView, @DrawableRes resource: Int) {
        imageView.setImageResource(resource)
    }

    @JvmStatic
    @BindingAdapter("srcColor")
    fun setSrcColor(view: ImageView, @ColorRes color: Int) {
        val drawable = view.drawable
        drawable.mutate()

        DrawableCompat.setTint(drawable, ResourcesCompat.getColor(view.resources, color, null))
        view.setImageDrawable(drawable)
    }

    @JvmStatic
    @BindingAdapter("drawableColor")
    fun setDrawableColor(textView: TextView, @ColorRes color: Int) {
        val drawables = textView.compoundDrawables
        for (drawable in drawables) {
            if (drawable != null) {
                drawable.mutate()
                DrawableCompat.setTint(
                    drawable,
                    ResourcesCompat.getColor(textView.resources, color, null)
                )
            }
        }
    }

    @JvmStatic
    @BindingAdapter("resTint")
    fun setTintResColor(imageView: ImageView, @ColorRes color: Int) {
        val rgbColor = ContextCompat.getColor(imageView.context, color)
        imageView.setColorFilter(rgbColor)
    }

    @JvmStatic
    @BindingAdapter("alphaCondition", "alphaValue", "enableAnimation")
    fun animateAlpha(
        view: View,
        alphaCondition: Boolean,
        alphaValue: Float,
        enableAnimation: Boolean
    ) {
        if (alphaCondition) {
            if (enableAnimation) {
                view.animate().alpha(alphaValue)
            } else {
                view.alpha = alphaValue
            }
        } else {
            if (enableAnimation) {
                view.animate().alpha(1.0f)
            } else {
                view.alpha = 1.0f
            }
        }
    }
}