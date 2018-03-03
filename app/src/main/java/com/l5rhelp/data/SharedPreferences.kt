package com.l5rhelp.data

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences (context: Context) {

    private val PREFS_NAME = "l5rhelp.sharedpreferences"
    private val SETTING_LOAD_PHOTO = "settings_load_photo"
    private val prefs: SharedPreferences? = context.getSharedPreferences(PREFS_NAME, 0)

    var loadPhoto: Boolean?
        get() = prefs?.getBoolean(SETTING_LOAD_PHOTO, true)
        set(value) = value?.let { prefs?.edit()?.putBoolean(SETTING_LOAD_PHOTO, it)?.apply() }!!

}