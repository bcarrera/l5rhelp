package com.l5rhelp.ui.presenter

import com.l5rhelp.data.RulingDao
import com.l5rhelp.domain.interactors.GetAllRulingsInteractor
import com.l5rhelp.domain.model.Ruling
import com.l5rhelp.domain.model.RulingsResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class RulesPresenter(val view: RulesPresenter.View,
                     val getAllRulingsInteractor: GetAllRulingsInteractor,
                     val rulingDao : RulingDao) : GetAllRulingsInteractor.Presenter {

    private var rulesList : MutableList<Ruling> = mutableListOf()

    fun initPresenter() {
        view.showLoading()
        getAllRulesFromDatabase()
    }

    fun getAllRulesFromDatabase () {
        doAsync {
            rulesList = rulingDao.getAllRulings()
            uiThread {
                if(rulesList.isEmpty()){
                    getAllRulingsInteractor.getAllRulingsInteractor(it)
                } else {
                    view.hideLoading()
                    view.initPresenterSuccess(rulesList.sortedWith(compareBy({ it.cardId.cardId })))
                }
            }
        }
    }

    fun addAllRulings(rulingsListForRoom: List<Ruling>?){
        doAsync {
            if (rulingsListForRoom != null) {
                for (ruling in rulingsListForRoom) {
                    rulingDao.insertRuling(ruling)
                    rulesList.add(ruling)
                }
            }

            uiThread {
                view.hideLoading()
                view.initPresenterSuccess(rulesList)
            }
        }
    }

    override fun getAllRulingsSuccess(response: RulingsResponse?) {
        addAllRulings(response?.records)
    }

    override fun getAllRulingsError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    interface View {
        fun showLoading()
        fun hideLoading()
        fun initPresenterSuccess(rulesList : List<Ruling>)
    }
}