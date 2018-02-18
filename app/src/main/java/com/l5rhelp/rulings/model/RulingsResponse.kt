package com.l5rhelp.rulings.model

import com.google.gson.annotations.SerializedName
import com.l5rhelp.cards.model.Card

class RulingsResponse (
    @SerializedName("records") val records: List<Ruling>,
    @SerializedName("size") val size: Int,
    @SerializedName("success") val success: Boolean
)