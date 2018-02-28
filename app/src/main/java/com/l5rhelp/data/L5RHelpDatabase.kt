package com.l5rhelp.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.l5rhelp.domain.model.Card
import com.l5rhelp.domain.model.Ruling

@Database(entities = arrayOf(Card::class, Ruling::class), version = 1)
abstract class L5RHelpDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
    abstract fun rulingDao(): RulingDao
}