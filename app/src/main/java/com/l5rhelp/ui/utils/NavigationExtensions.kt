package com.l5rhelp.ui.utils

import android.app.Activity
import android.content.Intent
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

inline fun <reified T : Activity> Activity.startActivity(id: String) {
    val intent = Intent(this, T::class.java)
    intent.putExtra("id", id)
    startActivity(intent)
}

fun AppCompatActivity.replaceFragmentSafely(fragment: Fragment,
                                            tag: String,
                                            allowStateLoss: Boolean = false,
                                            @IdRes containerViewId: Int
        //@AnimRes enterAnimation: Int = 0,
        //@AnimRes exitAnimation: Int = 0,
        //@AnimRes popEnterAnimation: Int = 0,
        //@AnimRes popExitAnimation: Int = 0
) {
    val ft = supportFragmentManager
            .beginTransaction()
            //.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
            .replace(containerViewId, fragment, tag)
    if (!supportFragmentManager.isStateSaved) {
        ft.commit()
    } else if (allowStateLoss) {
        ft.commitAllowingStateLoss()
    }
}

fun AppCompatActivity.addFragment(fragment: Fragment,
                                            tag: String,
                                            @IdRes containerViewId: Int
        //@AnimRes enterAnimation: Int = 0,
        //@AnimRes exitAnimation: Int = 0,
        //@AnimRes popEnterAnimation: Int = 0,
        //@AnimRes popExitAnimation: Int = 0
) {
            supportFragmentManager
            .beginTransaction()
            //.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
            .add(containerViewId, fragment, tag)
            .commit()
}