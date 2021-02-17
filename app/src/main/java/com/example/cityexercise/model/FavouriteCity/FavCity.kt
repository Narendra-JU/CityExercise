package com.example.cityexercise.model.FavouriteCity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavCity(
    val favcity: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}