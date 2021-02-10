package com.example.cityexercise.model.Login

import androidx.room.*

@Entity
data class User(val username:String,
    @ColumnInfo(name="password_hash")
    val passwordHash:Int) {
    @PrimaryKey(autoGenerate = true)
    var id:Long=0
}