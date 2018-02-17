package com.l5rhelp.dagger

import com.l5rhelp.main.presenter.MainPresenter
import com.l5rhelp.main.view.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainModule(val activity: MainActivity) {
    @Provides fun provideMainPresenter() = MainPresenter(activity)
}