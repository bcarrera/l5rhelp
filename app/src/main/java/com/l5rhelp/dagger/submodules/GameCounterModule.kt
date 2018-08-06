package com.l5rhelp.dagger.submodules

import com.l5rhelp.data.sharedPreferences.GameScores
import com.l5rhelp.ui.fragment.GameCounterFragment
import com.l5rhelp.ui.presenter.GameCounterPresenter
import dagger.Module
import dagger.Provides

@Module
class GameCounterModule(val fragment: GameCounterFragment) {

    @Provides
    fun provideGameScores(): GameScores = GameScores(fragment.context!!)

    @Provides
    fun provideGameCounterView(): GameCounterPresenter.View = fragment

    @Provides
    fun provideGameCounterPresenter(
            view: GameCounterPresenter.View,
            gameScores: GameScores
    ) = GameCounterPresenter(view, gameScores)
}