package com.example.ehu.weekckech.data.sql

import java.io.Serializable

data class TaskDataModel(
        val taskId: Int = -1,
        val isChecked: Boolean = false,
        val detail: String,
        val limitTime: String?,
        val notificationTime: String?,
        val weekGroup: Int
):Serializable