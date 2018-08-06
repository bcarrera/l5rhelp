package com.l5rhelp.domain.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "card_table")
@TypeConverters(PackCardConverter::class, StringConverter::class)
data class Card  (
        @SerializedName("clan") val clan : String,
        @SerializedName("cost") val cost: Int,
        @SerializedName("deck_limit") val deckLimit: Int,
        @PrimaryKey(autoGenerate = false)
        @SerializedName("id")
        val id: String,
        @SerializedName("influence_cost") val influenceCost: Int,
        @SerializedName("name") val name: String,
        @SerializedName("name_canonical") val nameCanonical: String,
        @SerializedName("pack_cards") val packCards: List<PackCard>,
        @SerializedName("side") val side: String,
        @SerializedName("text") val text: String?,
        @SerializedName("text_canonical") val textCanonical: String?,
        @SerializedName("traits") val traits: List<String>,
        @SerializedName("type") val type: String,
        @SerializedName("unicity") val unicity: Boolean,
        @SerializedName("military_bonus") val militaryBonus: String?,
        @SerializedName("political_bonus") val politicalBonus: String?,
        @SerializedName("military") val military: String?,
        @SerializedName("political") val political: String?,
        @SerializedName("glory") val glory: Int?

) : Serializable