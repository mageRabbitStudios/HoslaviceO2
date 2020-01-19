package com.kinzlstanislav.hoslaviceo2.architecture.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    @ColumnInfo val name: String,
    @ColumnInfo val phoneNumber: String,
    @ColumnInfo val isMaster: Boolean,
    @ColumnInfo val avatarUrl: String
)