package com.l5rhelp.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.l5rhelp.domain.model.Ruling

@Dao
interface RulingDao {

    @Query("SELECT * FROM ruling_table")
    fun getAllRulings(): MutableList<Ruling>

    @Query("SELECT * FROM ruling_table WHERE cardId LIKE :name")
    fun filterByName(name : String): MutableList<Ruling>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRuling(ruling: Ruling)

}