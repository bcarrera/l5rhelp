package com.l5rhelp.ui.presenter

import com.l5rhelp.data.sharedPreferences.GameScores


class GameCounterPresenter (val view: GameCounterPresenter.View,
                            private val gameScores: GameScores) {

    fun getAllGameScores () : GameScores {
        return gameScores
    }

    interface View {
        //fun initPresenter()
    }
}