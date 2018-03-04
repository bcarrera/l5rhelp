package com.l5rhelp.dagger.submodules

import com.l5rhelp.data.CardDao
import com.l5rhelp.data.SharedPreferences
import com.l5rhelp.domain.interactors.GetAllCardsInteractor
import com.l5rhelp.ui.presenter.MainPresenter
import com.l5rhelp.ui.activity.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainModule(val activity: MainActivity) {

    @Provides
    fun provideMainView(): MainPresenter.View = activity as MainPresenter.View

    @Provides
    fun provideMainPresenter(
            view: MainPresenter.View,
            getAllCardsInteractor: GetAllCardsInteractor,
            cardDao: CardDao,
            preferences: SharedPreferences
    ) = MainPresenter(view, getAllCardsInteractor, cardDao, preferences)
}