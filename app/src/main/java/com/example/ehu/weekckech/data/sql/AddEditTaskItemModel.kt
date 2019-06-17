package com.example.ehu.weekckech.data.sql

import java.util.*

/**
 * @constructor AddEditTaskPresenterで扱うListitemのModel
 * @property [imageId] itemに表示するリソースidです。
 * @property [componentType] AddEditTaskItemModelのcompanion objectを使用して
 */
data class AddEditTaskItemModel(val layoutid: Int, val imageId: Int, val componentType: Int, val text: String = "", val hintText: String = "Title", val spinnerItem: ArrayList<String>? = null, val selection: String = "") {
    companion object {
        const val EDITTEXT: Int = 0
        const val SPINNER: Int = 1
        const val TEXTVIEW: Int = 2
    }
}