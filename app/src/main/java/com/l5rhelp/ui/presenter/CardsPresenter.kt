package com.l5rhelp.ui.presenter


class CardsPresenter (val view: CardsPresenter.View) {



    fun initPresenter() {
        view.initPresenterSuccess()
    }

    interface View {
        fun initPresenterSuccess()
    }
}