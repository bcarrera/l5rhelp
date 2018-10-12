package com.l5rhelp.data.db

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

    @Query("SELECT * FROM card_table WHERE clan IN (:clanFilters) AND type IN (:typeFilters) AND side IN (:deckFilters) AND cost BETWEEN (:minCost) AND(:maxCost)")
    fun useFilters(clanFilters : List<String>, typeFilters : List<String>, deckFilters : List<String>, minCost:Int, maxCost:Int): MutableList<Card>

    @Query("SELECT count(*) FROM card_table")
    fun isTableEmpty(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCard(card: Card)

}