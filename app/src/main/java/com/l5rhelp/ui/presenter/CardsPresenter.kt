package com.l5rhelp.ui.presenter

import com.l5rhelp.data.CardDao
import com.l5rhelp.domain.model.Card
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class CardsPresenter (val view: CardsPresenter.View,
                      val cardDao : CardDao) {

    var cardList : List<Card> = emptyList()

    fun filterByName (name : String) {
        view.showLoading()

        doAsync {
            cardList = cardDao.filterByName("%$name%" )
            uiThread {
                view.filterByNameSuccess(cardList)
                view.hideLoading()
            }
        }
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun filterByNameSuccess(cardList : List<Card>)
    }
}