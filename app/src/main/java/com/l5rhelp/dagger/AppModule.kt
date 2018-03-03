package com.l5rhelp.dagger

import android.arch.persistence.room.Room
import android.content.Context
import com.l5rhelp.base.App
import com.l5rhelp.data.L5RHelpDatabase
import com.l5rhelp.data.SharedPreferences
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class AppModule(val app: App) {

    //Context
    @Provides @Singleton fun provideApp() = app

    @Provides @Singleton @ApplicationQualifier
    fun provideApplicationContext(): Context = app

    //Picasso
    @Provides @Singleton
    fun providePicasso(@ApplicationQualifier context: Context): Picasso = Picasso.Builder(context).build()


    //Room Database
    @Provides fun providesAppDatabase(@ApplicationQualifier context: Context): L5RHelpDatabase =
            Room.databaseBuilder(context, L5RHelpDatabase::class.java, "l5rHelp-db").build()

    @Provides fun providesCardDao(database: L5RHelpDatabase) = database.cardDao()

    @Provides fun providesRulingDao(database: L5RHelpDatabase) = database.rulingDao()

    //Preferences
    @Provides @Singleton
    fun provideSharedPreferences(): SharedPreferences = SharedPreferences(app)
}