package com.alacrity.giftesttask.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alacrity.giftesttask.util.Converters

@TypeConverters(Converters::class)
@Database(entities = [GifTableItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gifDao(): GifDao

}