package com.example.cityexercise.model.FavouriteCity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(FavCity::class), version = 1)
abstract class FavCityDatabase : RoomDatabase() {
    abstract fun FavCityDao(): FavCityDao

    companion object {
        @Volatile
        private var instance: FavCityDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(FavCityDatabase.LOCK) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FavCityDatabase::class.java,
            "favcitydatabase"
        ).build()
    }


}

