package com.l5rhelp.dagger.submodules

import com.l5rhelp.data.db.CardDao
import com.l5rhelp.data.db.RulingDao
import com.l5rhelp.data.sharedPreferences.SharedPreferences
import com.l5rhelp.domain.interactors.GetAllCardsInteractor
import com.l5rhelp.domain.interactors.GetAllRulingsInteractor
import com.l5rhelp.ui.fragment.SettingsFragment
import com.l5rhelp.ui.presenter.SettingsPresenter
import dagger.Module
import dagger.Provides

@Module
class SettingsModule(val fragment: SettingsFragment) {

    @Provides
    fun provideRulesView(): SettingsPresenter.View = fragment

    @Provides
    fun provideSettingsPresenter(
            view: SettingsPresenter.View,
            getAllRulingsInteractor: GetAllRulingsInteractor,
            rulingDao: RulingDao,
            getAllCardsInteractor: GetAllCardsInteractor,
            cardDao: CardDao,
            preferences: SharedPreferences
    ) = SettingsPresenter(view, getAllRulingsInteractor, rulingDao, getAllCardsInteractor, cardDao, preferences)
}