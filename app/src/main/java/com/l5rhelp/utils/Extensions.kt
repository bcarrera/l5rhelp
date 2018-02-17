@file:Suppress("unused")

package com.l5rhelp.utils

import android.app.Activity
import android.content.Context
import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.widget.Toast
import com.l5rhelp.base.App

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

//fun ImageView.loadUrl(url: String) {
//    Picasso.with(context).load(url).into(this)
//}

fun View.show() = run { visibility = View.VISIBLE }
fun View.hide() = run { visibility = View.GONE }

val Activity.app: App
    get() = application as App