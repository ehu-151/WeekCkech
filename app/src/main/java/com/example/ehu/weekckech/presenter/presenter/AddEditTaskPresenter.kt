package com.example.ehu.weekckech.presenter.presenter

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.AddEditTaskItemModel
import com.example.ehu.weekckech.data.sql.TaskDataModel
import com.example.ehu.weekckech.data.sql.room.AppDatabase
import com.example.ehu.weekckech.data.sql.room.RoomTask
import com.example.ehu.weekckech.presenter.contract.AddEditTaskContract
import java.util.*
import kotlin.concurrent.thread

class AddEditTaskPresenter(val addEditTaskView: AddEditTaskContract.View) : AddEditTaskContract.Presenter {


    override fun getEditTaskData() {
        val lists = listOf<Int>(R.id.edit_include_detail, R.id.edit_include_limittime,
                R.id.edit_include_notificationtime, R.id.edit_include_weekgroup)
        for (list in lists) {
            when (list) {
            }
        }
    }

    override fun loadTaskConfigEditRow() {
        val lists = java.util.ArrayList<AddEditTaskItemModel>()
        val model = AddEditTaskItemModel
        lists.add(AddEditTaskItemModel(R.id.edit_include_detail, R.drawable.ic_notes_white_24dp, model.EDITTEXT, hintText = "詳細"))
        lists.add(AddEditTaskItemModel(R.id.edit_include_limittime, R.drawable.ic_access_time_white_24dp, model.TEXTVIEW, text = "12:00"))
        lists.add(AddEditTaskItemModel(R.id.edit_include_notificationtime, R.drawable.ic_notifications_white_24dp, model.SPINNER, spinnerItem = arrayListOf("1H前", "2H前", "12時")))
        lists.add(AddEditTaskItemModel(R.id.edit_include_weekgroup, R.drawable.ic_today_white_24dp, model.SPINNER, spinnerItem = arrayListOf("月", "火", "水", "木", "金", "土", "日")))
        addEditTaskView.setTaskConfigEditRow(lists)
    }

    override fun saveTask(model: TaskDataModel, mContext: Context) {
        val db = Room.databaseBuilder(mContext, AppDatabase::class.java, "database-name").build()
        val task = RoomTask()
        task.taskId = Random().nextInt()
        task.isChecked = model.isChecked
        task.detail = model.detail
        task.limitTime = model.limitTime
        task.notificationTime = model.notificationTime
        task.weekGroup = model.weekGroup

        thread {
            db.roomTaskDao().insert(task)

            val result = db.roomTaskDao().getAll()

            result.forEachIndexed { index, it -> Log.d("resultDB",
                    "index$index " +
                            "${it.taskId} " +
                            "${it.detail} " +
                            "${it.limitTime} " +
                            "${it.limitTime} " +
                            "${it.notificationTime} " +
                            "${it.weekGroup} ") }
        }
    }

    override fun start() {
        loadTaskConfigEditRow()
    }

}