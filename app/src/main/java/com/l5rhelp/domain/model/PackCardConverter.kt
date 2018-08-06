package com.l5rhelp.domain.model

import android.arch.persistence.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import java.util.*

class PackCardConverter {
    var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<PackCard> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<PackCard>>() {

        }.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<PackCard>): String {
        return gson.toJson(someObjects)
    }
}