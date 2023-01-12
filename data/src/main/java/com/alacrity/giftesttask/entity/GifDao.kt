package com.alacrity.giftesttask.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GifDao {

        @Query("SELECT * FROM gifs")
        fun getAll(): List<GifTableItem>

        @Insert
        fun insertAll(vararg gifTableItem: GifTableItem)

        @Delete
        fun delete(gifTableItem: GifTableItem)


}