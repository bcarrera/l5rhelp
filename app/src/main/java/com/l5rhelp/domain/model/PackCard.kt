package com.l5rhelp.domain.model

import android.arch.persistence.room.Embedded
import com.google.gson.annotations.SerializedName

class PackCard (
        @SerializedName("illustrator") val illustrator: String,
        @SerializedName("image_url") val imageUrl: String,
        @Embedded
        @SerializedName("pack")
        val pack: Pack,
        @SerializedName("position") val position: String,
        @SerializedName("quantity") val quantity: Int,
        @SerializedName("flavor") val flavor: String
)