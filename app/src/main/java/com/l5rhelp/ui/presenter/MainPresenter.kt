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

    fun initPresenter() {
        view.showLoading()
        getAllCardsInteractor.getAllCardsInteractor(this)
    }

    override fun getAllCardsSuccess(response : CardsResponse?) {
        if(preferences.cardsDataBaseUpdate != response?.size) {
            addAllCards(response?.records)
            preferences.cardsDataBaseUpdate = response?.size
        } else {
            view.hideLoading()
            view.initPresenterSuccess()
        }
    }

    override fun getAllCardsError() {
        view.defaultError()
    }

    private fun addAllCards(cardsListForRoom: List<Card>?){
        doAsync {
            if (cardsListForRoom != null) {
                for (card in cardsListForRoom) cardDao.insertCard(card)
            }

            uiThread {
                view.hideLoading()
                view.initPresenterSuccess()
            }
        }
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun initPresenterSuccess()
        fun defaultError()
    }
}