package com.l5rhelp.base

import android.app.Application
import com.l5rhelp.dagger.AppComponent
import com.l5rhelp.dagger.AppModule
import com.l5rhelp.dagger.DaggerAppComponent

class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}