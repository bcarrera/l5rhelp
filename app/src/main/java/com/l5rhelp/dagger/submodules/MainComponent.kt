package com.l5rhelp.main.dagger

import com.l5rhelp.dagger.submodules.MainModule
import com.l5rhelp.ui.activity.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}