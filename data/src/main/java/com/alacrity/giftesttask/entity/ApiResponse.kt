package com.alacrity.giftesttask.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.beust.klaxon.*

private val klaxon = Klaxon()

data class ApiResponse(
    val data: List<Gif>,
    val pagination: Pagination,

    ) {

    companion object {
        fun fromJson(json: String) = klaxon.parse<ApiResponse>(json)
    }
}

data class Gif(
    val id: String,
    val images: Images,
)

data class Images(
    @Embedded
    val original: FixedHeight
)

data class FixedHeight(
    @ColumnInfo(name = "images")
    val url: String
)

data class Pagination(
    @Json(name = "total_count")
    val totalCount: Long,

    val count: Long,
    val offset: Long
)