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
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.concurrent.thread

class AddEditTaskPresenter(val addEditTaskView: AddEditTaskContract.View) : AddEditTaskContract.Presenter {
    val lists = java.util.ArrayList<AddEditTaskItemModel>()


    override fun getEditTaskData() {
        val lists = listOf<Int>(R.id.edit_include_detail, R.id.edit_include_limittime,
                R.id.edit_include_notificationtime, R.id.edit_include_weekgroup)
        for (list in lists) {
            when (list) {
            }
        }
    }

    override fun loadTaskConfigEditRow(model:TaskDataModel?) {
        val ITEM = AddEditTaskItemModel
        lists.add(AddEditTaskItemModel(R.id.edit_include_detail, R.drawable.ic_notes_white_24dp, ITEM.EDITTEXT, hintText = "詳細", text = model?.detail ?: "" ))
        lists.add(AddEditTaskItemModel(R.id.edit_include_limittime, R.drawable.ic_access_time_white_24dp, ITEM.TEXTVIEW, text = "12:00"))
        lists.add(AddEditTaskItemModel(R.id.edit_include_notificationtime, R.drawable.ic_notifications_white_24dp, ITEM.SPINNER, spinnerItem = arrayListOf("1H前", "2H前", "12時")))
        lists.add(AddEditTaskItemModel(R.id.edit_include_weekgroup, R.drawable.ic_today_white_24dp, ITEM.SPINNER, spinnerItem = arrayListOf("月", "火", "水", "木", "金", "土", "日")))
    }

    // 新規のタスク
    override fun saveTask(model: TaskDataModel, mContext: Context) {
        val db = Room.databaseBuilder(mContext, AppDatabase::class.java, "database-name").build()
        val task = RoomTask(taskId = (if (model.taskId > 0) model.taskId else Random().nextInt()),
                lastUpdate = Date(),
                isChecked = model.isChecked,
                detail = model.detail,
                limitTime = model.limitTime,
                notificationTime = model.notificationTime,
                weekGroup = model.weekGroup)

        runBlocking {
            thread {
                //　保存する
                db.roomTaskDao().insert(task)
                Log.d("DBInfo", "save:$task")
            }
        }
        addEditTaskView.showTasksMain()
    }

    override fun start() {
        addEditTaskView.setTaskConfigEditRow(lists)
    }

}