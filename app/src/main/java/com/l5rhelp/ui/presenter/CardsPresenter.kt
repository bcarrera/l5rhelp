package com.l5rhelp.ui.presenter

import com.l5rhelp.data.CardDao
import com.l5rhelp.domain.model.Card
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class CardsPresenter (val view: CardsPresenter.View,
                      val cardDao : CardDao) {

    private var cardList : List<Card> = emptyList()

    fun filterByName (name : String) {
        view.showLoading()

        doAsync {
            cardList = cardDao.filterByName("%$name%" )
            uiThread {
                view.filterSuccess(cardList)
                view.hideLoading()
            }
        }
    }

    fun useFilters (clanFilters : List<String>, typeFilters : List<String>, deckFilters : List<String>, cost: List<String>, traitsFilters : List<String>) {
        view.showLoading()

        doAsync {
            cardList = cardDao.useFilters(clanFilters, typeFilters, deckFilters, cost[0].toInt(), cost[1].toInt())
            uiThread {
                if(traitsFilters.isEmpty()){
                    view.filterSuccess(cardList)
                } else {
                    view.filterSuccess(cardList.filter { it.traits.containsAll(traitsFilters) })
                }
                view.hideLoading()
            }
        }
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun filterSuccess(cardList : List<Card>)
    }
}