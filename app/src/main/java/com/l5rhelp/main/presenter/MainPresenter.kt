package com.l5rhelp.main.presenter

/**
 * Created by Krupto on 17/02/2018.
 */
class MainPresenter(val view: View) {

    fun initPresenter() {
        view.initPresenterSuccess()
    }

    interface View {
        fun showProgress()
        fun hideProgress()
        fun initPresenterSuccess()
    }
}