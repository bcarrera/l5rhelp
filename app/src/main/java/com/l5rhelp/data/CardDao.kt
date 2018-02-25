package com.l5rhelp.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.l5rhelp.domain.model.Card

@Dao
interface CardDao {

    @Query("SELECT * FROM card_table")
    fun getAllCards(): MutableList<Card>

    @Query("SELECT * FROM card_table WHERE name LIKE :name")
    fun filterByName(name : String): MutableList<Card>

    @Query("SELECT * FROM card_table WHERE clan LIKE :clan AND type LIKE :type AND side LIKE :deck")
    fun useFilters(clan : String, type : String, deck : String): MutableList<Card>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCard(card: Card)

}