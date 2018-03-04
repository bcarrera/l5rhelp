package com.l5rhelp.ui.presenter

import com.l5rhelp.data.SharedPreferences

class SettingsPresenter (val view: SettingsPresenter.View, private val preferences: SharedPreferences ){

    fun loadPhoto () : Boolean? {
        return preferences.loadPhoto
    }

    fun setLoadPhoto (loadPhoto: Boolean) {
        preferences.loadPhoto = loadPhoto
    }

    fun getDataBasePeriodicity () : String? {
        return preferences.dataBasePeriodicity
    }

    fun setDataBasePeriodicity (dataBasePeriodicity: String) {
        preferences.dataBasePeriodicity = dataBasePeriodicity
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun upgradingDBSuccess()
        fun upgradingDBError()
    }
}