package com.l5rhelp.main.dagger

import com.l5rhelp.dagger.MainModule
import com.l5rhelp.main.view.MainActivity
import dagger.Subcomponent
import javax.inject.Singleton

@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}