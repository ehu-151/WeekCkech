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

class AddEditTaskPresenter(private val addEditTaskView: AddEditTaskContract.View) : AddEditTaskContract.Presenter {
    private val lists = ArrayList<AddEditTaskItemModel>()


    override fun getEditTaskData() {

    }

    override fun loadTaskConfigEditRow(model: TaskDataModel) {
        lists.add(AddEditTaskItemModel(R.id.edit_include_detail, R.drawable.ic_notes_white_24dp, AddEditTaskItemModel.EDITTEXT, hintText = "詳細", text = model?.detail
                ?: ""))
        lists.add(AddEditTaskItemModel(R.id.edit_include_limittime, R.drawable.ic_access_time_white_24dp, AddEditTaskItemModel.TEXTVIEW, text = model?.limitTime
                ?: "12:00"))
        lists.add(AddEditTaskItemModel(R.id.edit_include_notificationtime, R.drawable.ic_notifications_white_24dp, AddEditTaskItemModel.SPINNER, spinnerItem = arrayListOf("1H前", "2H前", "12時"), selection = model?.notificationTime
                ?: ""))
        lists.add(AddEditTaskItemModel(R.id.edit_include_weekgroup, R.drawable.ic_today_white_24dp, AddEditTaskItemModel.SPINNER, spinnerItem = arrayListOf("月", "火", "水", "木", "金", "土", "日"), selection = model?.weekGroup
                ?: ""))
    }

    // nullなら新規のタスクでsave,taskIdが既知なら更新
    override fun saveTask(taskDataModel: TaskDataModel, mContext: Context) {
        val db = Room.databaseBuilder(mContext, AppDatabase::class.java, AppDatabase.DB_NAME)
                .build()
        if (taskDataModel.taskId == null) {
            val task = RoomTask(taskId = Random().nextInt(),
                    lastUpdate = Date(),
                    isChecked = taskDataModel.isChecked,
                    detail = taskDataModel.detail,
                    limitTime = taskDataModel.limitTime,
                    notificationTime = arrayListOf(taskDataModel.notificationTime ?: ""),
                    weekGroup = taskDataModel.weekGroup)
            runBlocking {
                thread {
                    //　保存する
                    db.roomTaskDao().insert(task)
                    Log.d("DBInfo", "save:$task")
                }
            }

        } else {
            val task = RoomTask(taskId = taskDataModel.taskId,
                    lastUpdate = Date(),
                    isChecked = taskDataModel.isChecked,
                    detail = taskDataModel.detail,
                    limitTime = taskDataModel.limitTime,
                    notificationTime = arrayListOf(taskDataModel.notificationTime ?: ""),
                    weekGroup = taskDataModel.weekGroup)
            runBlocking {
                thread {
                    //　保存する
                    db.roomTaskDao().updateTasks(task)
                    Log.d("DBInfo", "save:$task")
                }
            }
        }
        addEditTaskView.showTasksMain()
    }

    override fun start() {
        addEditTaskView.setTaskConfigEditRow(lists)
    }

}