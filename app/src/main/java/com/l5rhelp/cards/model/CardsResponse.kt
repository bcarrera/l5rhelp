package com.l5rhelp.cards.model

import com.google.gson.annotations.SerializedName

class CardsResponse {

    @SerializedName("records") private val records: List<Card>? = null
    @SerializedName("size") private val size: Int? = null
    @SerializedName("success") private val success: Boolean? = null

}