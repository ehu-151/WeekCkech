package com.example.ehu.weekckech.data.sql

import android.support.v4.widget.TextViewCompat
import java.util.ArrayList

data class AddEditTaskItemModel(val imageId:Int, val hintText:String="Title", val textType:String, val spinnerItem: ArrayList<String>?=null)