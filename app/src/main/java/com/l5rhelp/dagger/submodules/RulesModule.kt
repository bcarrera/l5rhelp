package com.l5rhelp.dagger.submodules

import com.l5rhelp.data.RulingDao
import com.l5rhelp.data.sharedPreferences.SharedPreferences
import com.l5rhelp.domain.interactors.GetAllRulingsInteractor
import com.l5rhelp.ui.fragment.RulesFragment
import com.l5rhelp.ui.presenter.RulesPresenter
import dagger.Module
import dagger.Provides

@Module
class RulesModule(val fragment: RulesFragment) {

    @Provides
    fun provideRulesView(): RulesPresenter.View = fragment

    @Provides
    fun provideRulesPresenter(
            view: RulesPresenter.View,
            getAllRulingsInteractor: GetAllRulingsInteractor,
            rulingDao: RulingDao,
            preferences: SharedPreferences
    ) = RulesPresenter(view, getAllRulingsInteractor, rulingDao, preferences)
}