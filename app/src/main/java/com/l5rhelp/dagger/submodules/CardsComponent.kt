package com.l5rhelp.main.dagger

import com.l5rhelp.dagger.submodules.CardsModule
import com.l5rhelp.ui.fragment.CardsFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(CardsModule::class))
interface CardsComponent {
    fun inject(fragment : CardsFragment)
}