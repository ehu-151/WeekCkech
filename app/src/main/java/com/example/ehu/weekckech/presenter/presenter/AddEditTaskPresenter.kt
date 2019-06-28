package com.example.ehu.weekckech.presenter.presenter

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.ehu.weekckech.data.sql.TaskDataModel
import com.example.ehu.weekckech.data.sql.room.AppDatabase
import com.example.ehu.weekckech.data.sql.room.RoomTask
import com.example.ehu.weekckech.presenter.contract.AddEditTaskContract
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.concurrent.thread

class AddEditTaskPresenter(private val addEditTaskView: AddEditTaskContract.View) : AddEditTaskContract.Presenter {
    private var model: TaskDataModel? = null


    override fun getEditTaskData() {

    }

    override fun loadTaskConfigEditRow(model: TaskDataModel?) {
        this.model = model
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
                    notificationTime = taskDataModel.notificationTime,
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
                    notificationTime = taskDataModel.notificationTime,
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
        model?.let {
            // nullなら実行しない
            addEditTaskView.setTaskConfigEditRow(it)
        }
    }

}