package com.l5rhelp.domain.model

import com.google.gson.annotations.SerializedName

class CardsResponse {

    @SerializedName("records") val records: List<Card>? = null
    @SerializedName("size") val size: Int? = null
    @SerializedName("success") val success: Boolean? = null

}