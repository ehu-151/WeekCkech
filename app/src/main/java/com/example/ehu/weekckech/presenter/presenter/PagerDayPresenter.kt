package com.example.ehu.weekckech.presenter.presenter

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.ehu.weekckech.data.sql.TaskDataModel
import com.example.ehu.weekckech.data.sql.room.AppDatabase
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
                val db = Room.databaseBuilder(mContext, AppDatabase::class.java, "database-name").build()
                val result = db.roomTaskDao().getAll()

                result.forEach { list.add(TaskDataModel(it.isChecked, it.detail, it.limitTime, it.notificationTime, it.weekGroup)) }
                taskLiveData.postValue(list)
            }
        }
        pagerDayView.showDaysTasks(taskLiveData)
    }

    override fun completeDaysTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun activateDaysTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addNewDayTask() {
        pagerDayView.showAddEditTask()
    }

    override fun clearCompleteTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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