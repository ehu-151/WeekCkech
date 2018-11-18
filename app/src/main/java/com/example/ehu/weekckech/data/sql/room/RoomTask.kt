package com.example.ehu.weekckech.data.sql.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomTask {
    @PrimaryKey
    var taskId: Int=0

    @ColumnInfo(name = "isChecked")
    var isChecked: Boolean=false

    @ColumnInfo(name = "detail")
    lateinit var detail: String

    @ColumnInfo(name = "limitTime")
    var limitTime: String?=null

    @ColumnInfo(name = "notificationTime")
    var notificationTime: String?=null

    @ColumnInfo(name = "weekGroup")
    lateinit var weekGroup: String
}