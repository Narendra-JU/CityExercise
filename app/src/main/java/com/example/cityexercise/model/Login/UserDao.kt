package com.example.cityexercise.model.Login

import androidx.room.*
import com.example.cityexercise.model.Login.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User):Long
    @Query("SELECT * FROM user WHERE username=:username")
    suspend fun getUser(username:String): User
    @Query("DELETE FROM user WHERE id=:id")
    suspend fun deleteUser(id:Long)
}