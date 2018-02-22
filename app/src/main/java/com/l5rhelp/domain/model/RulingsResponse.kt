package com.l5rhelp.domain.model

import com.google.gson.annotations.SerializedName

class RulingsResponse (
        @SerializedName("records") val records: List<Ruling>,
        @SerializedName("size") val size: Int,
        @SerializedName("success") val success: Boolean
)