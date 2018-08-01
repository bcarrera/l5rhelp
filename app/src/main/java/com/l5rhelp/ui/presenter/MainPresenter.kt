package com.l5rhelp.ui.presenter

import com.l5rhelp.data.CardDao
import com.l5rhelp.data.sharedPreferences.SharedPreferences
import com.l5rhelp.domain.interactors.GetAllCardsInteractor
import com.l5rhelp.domain.model.Card
import com.l5rhelp.domain.model.CardsResponse
import com.l5rhelp.ui.utils.DataBasePeriodicity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainPresenter (val view: View,
                     val getAllCardsInteractor: GetAllCardsInteractor,
                     val cardDao: CardDao,
                     val preferences: SharedPreferences): GetAllCardsInteractor.Presenter {

    var cardList : List<Card> = emptyList()

    fun initPresenter() {
        view.showLoading()
        if(preferences.lastCardsDataBaseUpdate == 0L){
            getAllCardsInteractor.getAllCardsInteractor(this)
        } else {
            checkPeriodicity()
        }
    }

    override fun getAllCardsSuccess(response : CardsResponse?) {
        addAllCards(response?.records)
    }

    override fun getAllCardsError() {
        view.defaultError()
    }

    private fun getAllCardsFromDatabase () {
        doAsync {
            val tableCount : Int = cardDao.isTableEmpty()
            uiThread {
                if(tableCount == 0){
                    getAllCardsInteractor.getAllCardsInteractor(it)
                } else {
                    view.hideLoading()
                    view.initPresenterSuccess()
                }
            }
        }

    }

    fun getAllCards () : List<Card> {
        return cardList
    }

    private fun addAllCards(cardsListForRoom: List<Card>?){
        doAsync {
            if (cardsListForRoom != null) {
                for (card in cardsListForRoom) cardDao.insertCard(card)
            }
            preferences.lastCardsDataBaseUpdate = System.currentTimeMillis()

            uiThread {
                view.hideLoading()
                view.initPresenterSuccess()
            }
        }
    }

    private fun checkPeriodicity() {
        val currentDate = System.currentTimeMillis()
        when (preferences.dataBasePeriodicity) {
            DataBasePeriodicity.MONTHLY.name -> {
                if(currentDate - preferences.lastCardsDataBaseUpdate!! >= 1000*60*60*24*30f) {
                    getAllCardsInteractor.getAllCardsInteractor(this)
                } else {
                    getAllCardsFromDatabase()
                }
            }
            DataBasePeriodicity.WEEKLY.name -> {
                if(currentDate - preferences.lastCardsDataBaseUpdate!! >= 1000*60*60*24*7f) {
                    getAllCardsInteractor.getAllCardsInteractor(this)
                } else {
                    getAllCardsFromDatabase()
                }
            }
            DataBasePeriodicity.NEVER.name -> getAllCardsFromDatabase()
        }
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun initPresenterSuccess()
        fun defaultError()
    }
}