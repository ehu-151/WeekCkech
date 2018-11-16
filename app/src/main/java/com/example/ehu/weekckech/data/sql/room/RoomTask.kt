package com.example.ehu.weekckech.data.sql.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomTask(
        @PrimaryKey var taskId: Int,
        @ColumnInfo(name = "isChecked") var isChecked: Boolean?,
        @ColumnInfo(name = "detail") var detail: String?,
        @ColumnInfo(name = "limitDate") var limitDate: String?,
        @ColumnInfo(name = "notificationTime") var notificationTime: String?,
        @ColumnInfo(name = "weekGroup") var weekGroup: String?
)

