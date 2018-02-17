package com.l5rhelp.dagger

import com.l5rhelp.base.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class AppModule(val app: App) {
    @Provides @Singleton fun provideApp() = app
}