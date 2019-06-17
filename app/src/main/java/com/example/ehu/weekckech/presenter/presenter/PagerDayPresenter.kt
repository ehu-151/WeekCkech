package com.example.ehu.weekckech.presenter.presenter

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.ehu.weekckech.data.sql.TaskDataModel
import com.example.ehu.weekckech.data.sql.room.AppDatabase
import com.example.ehu.weekckech.data.sql.room.RoomTaskDao
import com.example.ehu.weekckech.presenter.contract.PagerDayConstract
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread


class PagerDayPresenter(val pagerDayView: PagerDayConstract.View, val mContext: Context) : PagerDayConstract.Presenter {

    var taskLiveData = MutableLiveData<ArrayList<TaskDataModel>>()
    /**
     * 全てのタスクを表示する
     */
    override fun loadDaysTasks() {
        val list = ArrayList<TaskDataModel>()
        runBlocking {
            thread {
                val result = getDao().getAll()

                result.forEach { list.add(TaskDataModel(it.taskId, it.isChecked, it.detail, it.limitTime, it.notificationTime, it.weekGroup)) }
                taskLiveData.postValue(list)
            }
        }
        pagerDayView.showDaysTasks(taskLiveData)
    }

    // taskをcheckにする
    override fun checkTask(taskId: Int) {
        runBlocking {
            thread {
                getDao().changeTaskCheck(taskId, true)
            }
        }
    }

    // taskをunCheckにする
    override fun unCheckTask(taskId: Int) {
        runBlocking {
            thread {
                getDao().changeTaskCheck(taskId, false)
            }
        }
    }

    override fun addNewDayTask() {
        pagerDayView.showAddTask()
    }

    override fun editDayTask(model: TaskDataModel) {
        pagerDayView.showEditTask(model)

    }

    override fun clearCompleteTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun getDao(): RoomTaskDao {
        return Room.databaseBuilder(mContext, AppDatabase::class.java, "database-name").build().roomTaskDao()
    }

    /**
     * プレゼンターが初めにやること
     *
     * タスクのリロード
     */
    override fun start() {
        loadDaysTasks()
    }

}