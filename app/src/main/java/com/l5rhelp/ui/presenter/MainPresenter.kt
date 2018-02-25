package com.l5rhelp.ui.presenter

import com.l5rhelp.data.CardDao
import com.l5rhelp.domain.interactors.GetAllCardsInteractor
import com.l5rhelp.domain.model.Card
import com.l5rhelp.domain.model.CardsResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainPresenter (val view: View,
                     val getAllCardsInteractor: GetAllCardsInteractor,
                     val cardDao: CardDao): GetAllCardsInteractor.Presenter {

    var cardList : List<Card> = emptyList()

    fun initPresenter() {
        view.showLoading()
        getAllCardsFromDatabase()
    }

    override fun getAllCardsSuccess(response : CardsResponse?) {
        addAllCards(response?.records)
    }

    override fun getAllCardsError() {
        //TODO Error genérico
    }

    fun getAllCardsFromDatabase () {
        doAsync {
            cardList = cardDao.getAllCards()
            uiThread {
                if(cardList.isEmpty()){
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

    fun addAllCards(cardsListForRoom: List<Card>?){
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
    }
}