package com.l5rhelp.ui.presenter

import com.l5rhelp.data.db.CardDao
import com.l5rhelp.data.db.RulingDao
import com.l5rhelp.data.sharedPreferences.SharedPreferences
import com.l5rhelp.domain.interactors.GetAllCardsInteractor
import com.l5rhelp.domain.interactors.GetAllRulingsInteractor
import com.l5rhelp.domain.model.Card
import com.l5rhelp.domain.model.CardsResponse
import com.l5rhelp.domain.model.Ruling
import com.l5rhelp.domain.model.RulingsResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SettingsPresenter(val view: SettingsPresenter.View, val getAllRulingsInteractor: GetAllRulingsInteractor, val rulingDao: RulingDao,
                        val getAllCardsInteractor: GetAllCardsInteractor, val cardDao: CardDao,
                        private val preferences: SharedPreferences) : GetAllCardsInteractor.Presenter, GetAllRulingsInteractor.Presenter {

    fun loadPhoto(): Boolean? {
        return preferences.loadPhoto
    }

    fun setLoadPhoto(loadPhoto: Boolean) {
        preferences.loadPhoto = loadPhoto
    }

    //Actualizar cartas

    fun updateCards() {
        view.showLoading()
        getAllCardsInteractor.getAllCardsInteractor(this)
    }

    override fun getAllCardsSuccess(response: CardsResponse?) {
        addAllCards(response?.records)
    }

    override fun getAllCardsError() {
        view.upgradingDBError()
    }

    private fun addAllCards(cardsListForRoom: List<Card>?) {
        doAsync {
            if (cardsListForRoom != null) {
                for (card in cardsListForRoom) cardDao.insertCard(card)
            }

            uiThread {
                view.hideLoading()
                view.upgradingCardsDBSuccess()
            }
        }
    }

    //Actualizar reglas

    fun updateRules() {
        view.showLoading()
        getAllRulingsInteractor.getAllRulingsInteractor(this)
    }

    override fun getAllRulingsSuccess(response: RulingsResponse?) {
        addAllRulings(response?.records)
    }

    override fun getAllRulingsError() {
        view.upgradingDBError()
    }

    private fun addAllRulings(rulingsListForRoom: List<Ruling>?) {
        doAsync {
            if (rulingsListForRoom != null) {
                for (ruling in rulingsListForRoom) {
                    rulingDao.insertRuling(ruling)
                }
            }

            uiThread {
                view.hideLoading()
                view.upgradingRulesDBSuccess()
            }
        }
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun upgradingCardsDBSuccess()
        fun upgradingDBError()
        fun upgradingRulesDBSuccess()
    }
}