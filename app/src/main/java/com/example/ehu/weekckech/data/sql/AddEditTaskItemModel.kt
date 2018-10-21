package com.example.ehu.weekckech.data.sql

import java.util.*

/**
 * @constructor AddEditTaskPresenterで扱うListitemのModel
 * @property [imageId] itemに表示するリソースidです。
 * @property [componentType] AddEditTaskItemModelのcompanion objectを使用して
 */
data class AddEditTaskItemModel(val layoutid: Int, val imageId: Int, val componentType: Int, val hintText: String = "Title", val spinnerItem: ArrayList<String>? = null) {
    companion object {
        const val TEXTVIEW: Int = 0
        const val SPINNER: Int = 1
    }
}