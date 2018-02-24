package com.l5rhelp.dagger.submodules

import com.l5rhelp.data.CardDao
import com.l5rhelp.domain.interactors.GetAllCardsInteractor
import com.l5rhelp.ui.presenter.MainPresenter
import com.l5rhelp.ui.activity.MainActivity
import com.l5rhelp.ui.fragment.CardsFragment
import com.l5rhelp.ui.presenter.CardsPresenter
import dagger.Module
import dagger.Provides

@Module
class CardsModule(val fragment: CardsFragment) {

    @Provides
    fun provideCardsView(): CardsPresenter.View = fragment

    @Provides
    fun provideCardsPresenter(
            view: CardsPresenter.View,
            cardDao: CardDao
    ) = CardsPresenter(view, cardDao)
}