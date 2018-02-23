package com.l5rhelp.dagger


import com.l5rhelp.base.App
import com.l5rhelp.dagger.submodules.MainModule
import com.l5rhelp.main.dagger.MainComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class), (InteractorsModule::class)])
interface AppComponent {
    fun inject(app: App)
    fun plus(mainModule: MainModule): MainComponent
}