package com.l5rhelp.base

import android.app.Application
import com.l5rhelp.dagger.AppComponent
import com.l5rhelp.dagger.AppModule
import com.l5rhelp.dagger.DaggerAppComponent
import com.l5rhelp.data.SharedPreferences

class App : Application() {

    companion object {
        lateinit var preferences: SharedPreferences
    }

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        preferences = SharedPreferences(applicationContext)
        component.inject(this)
    }
}