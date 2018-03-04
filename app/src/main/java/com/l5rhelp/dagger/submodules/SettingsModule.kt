package com.l5rhelp.dagger.submodules

import com.l5rhelp.data.SharedPreferences
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
            preferences: SharedPreferences
    ) = SettingsPresenter(view, preferences)
}