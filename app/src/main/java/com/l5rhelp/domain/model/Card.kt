package com.l5rhelp.domain.model

import com.google.gson.annotations.SerializedName

class Card (
        @SerializedName("clan") val clan : String,
        @SerializedName("cost") val cost: Int,
        @SerializedName("deck_limit") val deckLimit: Int,
        @SerializedName("id") val id: String,
        @SerializedName("influence_cost") val influenceCost: Int,
        @SerializedName("name") val name: String,
        @SerializedName("name_canonical") val nameCanonical: String,
        @SerializedName("pack_cards") val packCards: List<PackCard>,
        @SerializedName("side") val side: String,
        @SerializedName("text") val text: String,
        @SerializedName("text_canonical") val textCanonical: String,
        @SerializedName("traits") val traits: List<String>,
        @SerializedName("type") val type: String,
        @SerializedName("unicity") val unicity: Boolean,
        @SerializedName("military_bonus") val militaryBonus: String,
        @SerializedName("political_bonus") val politicalBonus: String
)