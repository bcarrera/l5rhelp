package com.l5rhelp.rulings.model

import com.google.gson.annotations.SerializedName

class Ruling (
        @SerializedName("card") val cardId: CardId,
        @SerializedName("id") val id : Int,
        @SerializedName("link") val link : String,
        @SerializedName("source") val source : String,
        @SerializedName("text") val text : String
)