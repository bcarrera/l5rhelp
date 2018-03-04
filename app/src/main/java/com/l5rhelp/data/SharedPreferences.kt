package com.l5rhelp.data

import android.content.Context
import android.content.SharedPreferences
import com.l5rhelp.ui.utils.DataBasePeriodicity

class SharedPreferences (context: Context) {

    companion object {
        private const val PREFS_NAME = "l5rhelp.sharedpreferences"
        private const val SETTING_LOAD_PHOTO = "settings_load_photo"
        private const val SETTING_DATABASE_PERIODICITY = "settings_database_periodicity"
        private const val SETTING_DATABASE_LAST_CARDS_UPDATE = "settings_database_last_cards_update"
        private const val SETTING_DATABASE_LAST_RULES_UPDATE = "settings_database_last_rules_update"
    }

    private val prefs: SharedPreferences? = context.getSharedPreferences(PREFS_NAME, 0)

    var loadPhoto: Boolean?
        get() = prefs?.getBoolean(SETTING_LOAD_PHOTO, true)
        set(value) = value?.let { prefs?.edit()?.putBoolean(SETTING_LOAD_PHOTO, it)?.apply() }!!

    var dataBasePeriodicity: String?
        get() = prefs?.getString(SETTING_DATABASE_PERIODICITY, DataBasePeriodicity.MONTHLY.name)
        set(value) = value?.let { prefs?.edit()?.putString(SETTING_DATABASE_PERIODICITY, it)?.apply() }!!

    var lastCardsDataBaseUpdate: Long?
        get() = prefs?.getLong(SETTING_DATABASE_LAST_CARDS_UPDATE, 0)
        set(value) = value?.let { prefs?.edit()?.putLong(SETTING_DATABASE_LAST_CARDS_UPDATE, it)?.apply() }!!

    var lastRulesDataBaseUpdate: Long?
        get() = prefs?.getLong(SETTING_DATABASE_LAST_RULES_UPDATE, 0)
        set(value) = value?.let { prefs?.edit()?.putLong(SETTING_DATABASE_LAST_RULES_UPDATE, it)?.apply() }!!
}