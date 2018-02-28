package com.l5rhelp.main.dagger

import com.l5rhelp.dagger.submodules.CardsModule
import com.l5rhelp.dagger.submodules.RulesModule
import com.l5rhelp.ui.fragment.CardsFragment
import com.l5rhelp.ui.fragment.RulesFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(RulesModule::class))
interface RulesComponent {
    fun inject(fragment : RulesFragment)
}