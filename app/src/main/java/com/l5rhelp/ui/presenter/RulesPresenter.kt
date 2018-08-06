package com.l5rhelp.ui.presenter

import com.l5rhelp.data.RulingDao
import com.l5rhelp.data.sharedPreferences.SharedPreferences
import com.l5rhelp.domain.interactors.GetAllRulingsInteractor
import com.l5rhelp.domain.model.Ruling
import com.l5rhelp.domain.model.RulingsResponse
import com.l5rhelp.ui.utils.DataBasePeriodicity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*


class RulesPresenter(val view: RulesPresenter.View,
                     val getAllRulingsInteractor: GetAllRulingsInteractor,
                     val rulingDao : RulingDao,
                     val preferences: SharedPreferences) : GetAllRulingsInteractor.Presenter {

    private var rulesList : MutableList<Ruling> = mutableListOf()

    fun initPresenter() {
        view.showLoading()
        getAllRulingsInteractor.getAllRulingsInteractor(this)
    }

    private fun addAllRulings(rulingsListForRoom: List<Ruling>?){
        doAsync {
            if (rulingsListForRoom != null) {
                for (ruling in rulingsListForRoom) {
                    rulingDao.insertRuling(ruling)
                    rulesList.add(ruling)
                }
            }

            uiThread {
                view.hideLoading()
                view.initPresenterSuccess()
            }
        }
    }

    override fun getAllRulingsSuccess(response: RulingsResponse?) {
        if(preferences.rulesDataBaseUpdate != response?.size){
            addAllRulings(response?.records)
            preferences.rulesDataBaseUpdate = response?.size
        } else {
            view.hideLoading()
            view.initPresenterSuccess()
        }
    }

    override fun getAllRulingsError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun filterByName (name : String) {
        view.showLoading()

        doAsync {
            rulesList = rulingDao.filterByName("%$name%" )
            uiThread {
                view.filterSuccess(rulesList.sortedWith(compareBy({ it.cardId.cardId })))
                view.hideLoading()
            }
        }
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun filterSuccess(rulingList : List<Ruling>)
        fun initPresenterSuccess()
    }
}