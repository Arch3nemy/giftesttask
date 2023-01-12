package com.alacrity.giftesttask.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gifs")
class GifTableItem(
    @PrimaryKey val id: String,
    @Embedded
    val images: Images
)

fun GifTableItem.toRawItem(): Gif {
    return Gif(id, images)
}

fun List<GifTableItem>.toRawItems(): MutableList<Gif> {
    val result = mutableListOf<Gif>()
    forEach {
        result.add(it.toRawItem())
    }
    return result
}

