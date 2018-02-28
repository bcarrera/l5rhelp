package com.l5rhelp.dagger

import com.l5rhelp.base.net.FiveRingsDBService
import com.l5rhelp.domain.interactors.GetAllCardsInteractor
import com.l5rhelp.domain.interactors.GetAllRulingsInteractor
import dagger.Module
import dagger.Provides

@Module
class InteractorsModule {

    @Provides
    fun provideGetAllCardsInteractor(fiveRingsDBService: FiveRingsDBService) = GetAllCardsInteractor(fiveRingsDBService)

    @Provides
    fun provideGetAllRulingsInteractor(fiveRingsDBService: FiveRingsDBService) = GetAllRulingsInteractor(fiveRingsDBService)
}