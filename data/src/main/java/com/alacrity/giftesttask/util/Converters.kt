package com.alacrity.giftesttask.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.alacrity.giftesttask.entity.FixedHeight
import com.alacrity.giftesttask.entity.Images
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

interface JsonParser {
    fun <T> fromJson(json: String, type: Type): T?
    fun <T> toJson(obj: T, type: Type): String?
}

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun toImagesJson(meaning: List<Images>) : String {
        return jsonParser.toJson(
            meaning,
            object : TypeToken<ArrayList<Images>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromImagesJson(json: String): List<Images>{
        return jsonParser.fromJson<ArrayList<Images>>(
            json,
            object: TypeToken<ArrayList<Images>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toFixedHeightJson(meaning: List<FixedHeight>) : String {
        return jsonParser.toJson(
            meaning,
            object : TypeToken<ArrayList<FixedHeight>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromFixedHeightJson(json: String): List<FixedHeight> {
        return jsonParser.fromJson<ArrayList<FixedHeight>>(
            json,
            object: TypeToken<ArrayList<FixedHeight>>(){}.type
        ) ?: emptyList()
    }
}