package com.example.ehu.weekckech.data.sql

import java.io.Serializable
import java.util.*

data class TaskDataModel(
        val taskId: Int? = null,
        val isChecked: Boolean = false,
        val detail: String,
        val limitTime: String?,
        val notificationTime: ArrayList<String>? = null,
        val weekGroup: String
) : Serializable