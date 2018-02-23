@file:Suppress("unused")

package com.l5rhelp.ui.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.l5rhelp.base.App
import com.squareup.picasso.Picasso

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ImageView.loadUrl(url: String) {
    Picasso.with(context).load(url).into(this)
}

fun View.show() = run { visibility = View.VISIBLE }
fun View.hide() = run { visibility = View.GONE }

val Activity.app: App
    get() = application as App