package com.l5rhelp.dagger

import com.l5rhelp.domain.interactors.GetAllCardsInteractor
import com.l5rhelp.main.presenter.MainPresenter
import com.l5rhelp.main.view.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainModule(val activity: MainActivity) {

    @Provides
    fun provideMainView(): MainPresenter.View = activity as MainPresenter.View

    @Provides
    fun provideMainPresenter(
            view: MainPresenter.View,
            getAllCardsInteractor: GetAllCardsInteractor
    ) = MainPresenter(view, getAllCardsInteractor)
}