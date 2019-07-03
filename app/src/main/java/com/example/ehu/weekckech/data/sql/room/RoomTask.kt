package com.example.ehu.weekckech.data.sql.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class RoomTask(
        @PrimaryKey
        val taskId: Int = Random().nextInt(),

        val lastUpdate: Date?,

        val isChecked: Boolean = false,

        val detail: String,

        val limitTime: String? = null,

        val notificationTime: ArrayList<String>? = null,

        val weekGroup: Int
)