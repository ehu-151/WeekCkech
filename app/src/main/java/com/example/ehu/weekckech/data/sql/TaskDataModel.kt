package com.example.ehu.weekckech.data.sql

import java.io.Serializable
import java.util.*

data class TaskDataModel(
        val taskId: Int? = null,
        val isChecked: Boolean = false,
        val detail: String,
        val limitTime: String?,
        val notificationTime: ArrayList<String>? = null,
        val weekGroup: Int
) : Serializable {
    companion object {
        fun toDayOfWeek(day: Int): String {
            val week = mapOf(
                    0 to "日",
                    1 to "月",
                    2 to "火",
                    3 to "水",
                    4 to "木",
                    5 to "金",
                    6 to "土"
            )
            return week.get(day).toString()
        }
    }
}