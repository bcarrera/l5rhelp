package com.l5rhelp.main.dagger

import com.l5rhelp.dagger.submodules.SettingsModule
import com.l5rhelp.ui.fragment.SettingsFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(SettingsModule::class))
interface SettingsComponent {
    fun inject(fragment : SettingsFragment)
}