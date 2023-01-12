package com.alacrity.giftesttask.repository

import com.alacrity.giftesttask.entity.ApiResponse
import com.alacrity.giftesttask.entity.Gif

interface Repository {

    suspend fun getGifs(query: String, offSet: Int, limit: Int): ApiResponse

    suspend fun getGifsFromDB(): List<Gif>

    suspend fun saveItemToDB(gif: Gif)

    suspend fun removeGifFromDB(gif: Gif)
}