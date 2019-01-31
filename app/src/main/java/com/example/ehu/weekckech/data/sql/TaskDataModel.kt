package com.example.ehu.weekckech.data.sql

data class TaskDataModel(
        val isChecked: Boolean = false,
        val detail: String,
        val limitTime: String?,
        val notificationTime: String?,
        val weekGroup: Int
)