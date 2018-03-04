package com.l5rhelp.ui.presenter

import com.l5rhelp.data.RulingDao
import com.l5rhelp.data.SharedPreferences
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
        if(preferences.lastRulesDataBaseUpdate == 0L){
            getAllRulingsInteractor.getAllRulingsInteractor(this)
        } else {
            checkPeriodicity()
        }    }

    private fun getAllRulesFromDatabase () {
        doAsync {
            val tableCount : Int = rulingDao.isTableEmpty()
            uiThread {
                if(tableCount == 0){
                    getAllRulingsInteractor.getAllRulingsInteractor(it)
                } else {
                    view.hideLoading()
                    view.initPresenterSuccess()
                }
            }
        }
    }

    private fun addAllRulings(rulingsListForRoom: List<Ruling>?){
        doAsync {
            if (rulingsListForRoom != null) {
                for (ruling in rulingsListForRoom) {
                    rulingDao.insertRuling(ruling)
                    rulesList.add(ruling)
                }
                preferences.lastRulesDataBaseUpdate = System.currentTimeMillis()
            }

            uiThread {
                view.hideLoading()
                view.initPresenterSuccess()
            }
        }
    }

    override fun getAllRulingsSuccess(response: RulingsResponse?) {
        addAllRulings(response?.records)
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

    private fun checkPeriodicity() {
        val currentDate = System.currentTimeMillis()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = currentDate
        when (preferences.dataBasePeriodicity) {
            DataBasePeriodicity.MONTHLY.name -> {
                if(currentDate - preferences.lastRulesDataBaseUpdate!! >= 1000*60*60*24*30f) {
                    getAllRulingsInteractor.getAllRulingsInteractor(this)
                } else {
                    getAllRulesFromDatabase()
                }
            }
            DataBasePeriodicity.WEEKLY.name -> {
                if(currentDate - preferences.lastRulesDataBaseUpdate!! >= 1000*60*60*24*7f) {
                    getAllRulingsInteractor.getAllRulingsInteractor(this)
                } else {
                    getAllRulesFromDatabase()
                }
            }
            DataBasePeriodicity.NEVER.name -> getAllRulesFromDatabase()
        }
    }


    interface View {
        fun showLoading()
        fun hideLoading()
        fun filterSuccess(rulingList : List<Ruling>)
        fun initPresenterSuccess()
    }
}