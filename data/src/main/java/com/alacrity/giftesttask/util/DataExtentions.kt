package com.alacrity.giftesttask.util

import com.alacrity.giftesttask.entity.Gif
import com.alacrity.giftesttask.entity.GifTableItem

fun Gif.toTableItem(): GifTableItem {
    return GifTableItem(id, images)
}
