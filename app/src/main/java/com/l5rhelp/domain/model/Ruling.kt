package com.l5rhelp.domain.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ruling_table")
class Ruling (
        @Embedded
        @SerializedName("card")
        val cardId: CardId,
        @PrimaryKey(autoGenerate = false)
        @SerializedName("id")
        val id : Int,
        @SerializedName("link") val link : String?,
        @SerializedName("source") val source : String,
        @SerializedName("text") val text : String
)