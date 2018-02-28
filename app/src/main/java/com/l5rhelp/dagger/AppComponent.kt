package com.l5rhelp.dagger


import com.l5rhelp.base.App
import com.l5rhelp.dagger.submodules.CardsModule
import com.l5rhelp.dagger.submodules.MainModule
import com.l5rhelp.dagger.submodules.RulesModule
import com.l5rhelp.main.dagger.CardsComponent
import com.l5rhelp.main.dagger.MainComponent
import com.l5rhelp.main.dagger.RulesComponent
import com.l5rhelp.ui.fragment.CardsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class), (InteractorsModule::class)])
interface AppComponent {
    fun inject(app: App)
    fun plus(mainModule: MainModule): MainComponent
    fun plus(cardsFragment: CardsModule): CardsComponent
    fun plus(rulesFragment: RulesModule):RulesComponent
}