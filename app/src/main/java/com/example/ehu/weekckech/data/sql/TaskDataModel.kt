package com.example.ehu.weekckech.data.sql

data class TaskDataModel(
        val taskId: Int = -1,
        val isChecked: Boolean = false,
        val detail: String,
        val limitTime: String?,
        val notificationTime: String?,
        val weekGroup: Int
)