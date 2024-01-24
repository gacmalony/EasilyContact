package com.example.easilycontact.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface UserDAO {

    @Insert
    suspend fun insertUser(user: room):Long


    @Update
    suspend fun updateUser(user:room)


    @Delete
    suspend fun deleteUser(user:room)

    @Query("DELETE FROM MyTable")
    suspend fun deleteAll()

    @Query("SELECT * FROM MyTable")
    fun getAllUsersInDatabase(): LiveData<List<room>>
}