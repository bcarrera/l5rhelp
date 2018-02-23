package com.l5rhelp.ui.presenter

import com.l5rhelp.data.CardDao
import com.l5rhelp.domain.interactors.GetAllCardsInteractor
import com.l5rhelp.domain.model.Card
import com.l5rhelp.domain.model.CardsResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Krupto on 17/02/2018.
 */
class MainPresenter (val view: View,
                     val getAllCardsInteractor: GetAllCardsInteractor,
                     val cardDao: CardDao): GetAllCardsInteractor.Presenter {

    var cardList : List<Card> = emptyList()

    fun initPresenter() {
        //getAllCardsInteractor.getAllCardsInteractor(this)
        //getAllCardsFromDatabase()
    }

    override fun getAllCardsSuccess(response : CardsResponse?) {
        addAllCards(response?.records)
        view.initPresenterSuccess()
    }

    override fun getAllCardsError() {
        //TODO Error genérico
    }

    fun getAllCardsFromDatabase () {
        doAsync {
            cardList = cardDao.getAllCards()
            uiThread {
                getAllCards()
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
               val sdfs = ""
            }
        }
    }

    interface View {
        fun showProgress()
        fun hideProgress()
        fun initPresenterSuccess()
    }
}