package com.kinzlstanislav.hoslaviceo2.architecture.repository.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kinzlstanislav.hoslaviceo2.architecture.repository.model.User

@Dao
interface UsersDao {

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Insert
    suspend fun insertAll(users: List<User>)

    @Delete
    suspend fun delete(user: User)

    @Query("DELETE FROM user")
    suspend fun nukeTable()
}