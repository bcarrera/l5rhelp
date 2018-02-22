package com.l5rhelp.dagger

import com.l5rhelp.domain.interactors.GetAllCardsInteractor
import dagger.Module
import dagger.Provides

@Module
class InteractorsModule {

    @Provides
    fun provideGetAllCardsInteractor() = GetAllCardsInteractor()

}