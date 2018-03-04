package com.l5rhelp.dagger


import com.l5rhelp.base.App
import com.l5rhelp.dagger.submodules.CardsModule
import com.l5rhelp.dagger.submodules.MainModule
import com.l5rhelp.dagger.submodules.RulesModule
import com.l5rhelp.dagger.submodules.SettingsModule
import com.l5rhelp.main.dagger.CardsComponent
import com.l5rhelp.main.dagger.MainComponent
import com.l5rhelp.main.dagger.RulesComponent
import com.l5rhelp.main.dagger.SettingsComponent
import com.l5rhelp.ui.fragment.CardDetailFragment
import com.l5rhelp.ui.fragment.SettingsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class), (InteractorsModule::class)])
interface AppComponent {
    fun inject(app: App)

    //Only app
    fun inject(cardsDetailFragment: CardDetailFragment)

    //Submodules
    fun plus(mainModule: MainModule): MainComponent
    fun plus(cardsFragment: CardsModule): CardsComponent
    fun plus(rulesFragment: RulesModule):RulesComponent
    fun plus(settingsFragment: SettingsModule):SettingsComponent
}