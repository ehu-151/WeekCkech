package com.example.ehu.weekckech.data.sql.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RoomTask::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    // DAOを取得する。
    abstract fun roomTaskDao(): RoomTaskDao
}