package com.l5rhelp.main.dagger

import com.l5rhelp.dagger.submodules.GameCounterModule
import com.l5rhelp.ui.fragment.GameCounterFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(GameCounterModule::class))
interface GameCounterComponent {
    fun inject(fragment : GameCounterFragment)
}