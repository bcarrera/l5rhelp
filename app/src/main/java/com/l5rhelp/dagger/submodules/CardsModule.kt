package com.l5rhelp.dagger.submodules

import com.l5rhelp.data.db.CardDao
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