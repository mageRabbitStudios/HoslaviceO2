package com.kinzlstanislav.hoslaviceo2.architecture.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kinzlstanislav.hoslaviceo2.architecture.repository.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}