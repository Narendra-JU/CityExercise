package com.example.cityexercise.model.FavouriteCity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FavCityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavCity(favCity: FavCity): Long

    @Query("SELECT * FROM FavCity WHERE favcity=:favcity")
    suspend fun getFavCity(favcity: String): FavCity

    @Query("SELECT * FROM FavCity")
    fun getCityList(): List<FavCity>


}