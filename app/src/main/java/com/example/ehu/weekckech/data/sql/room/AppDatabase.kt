package com.example.ehu.weekckech.data.sql.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

@Database(entities = arrayOf(RoomTask::class), version = 1)
@TypeConverters(DateTimeConverter::class, ArrayListStringConverter::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "todo-database-0.1"

    }

    // DAOを取得する。
    abstract fun roomTaskDao(): RoomTaskDao
}

class DateTimeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}

class ArrayListStringConverter {
    @TypeConverter
    fun fromString(value: String): ArrayList<String>? {
        val listType = object : TypeToken<ArrayList<String>>() {

        }.getType()
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}