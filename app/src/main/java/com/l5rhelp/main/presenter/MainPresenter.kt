package com.l5rhelp.main.presenter

import com.l5rhelp.domain.interactors.GetAllCardsInteractor
import com.l5rhelp.domain.model.CardsResponse

/**
 * Created by Krupto on 17/02/2018.
 */
class MainPresenter (val view: View,
                    val getAllCardsInteractor: GetAllCardsInteractor): GetAllCardsInteractor.Presenter {

    fun initPresenter() {
        view.initPresenterSuccess()
        getAllCardsInteractor.getAllCardsInteractor(this)
    }

    override fun getAllCardsSuccess(response : CardsResponse?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllCardsError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    interface View {
        fun showProgress()
        fun hideProgress()
        fun initPresenterSuccess()
    }
}