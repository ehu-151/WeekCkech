package com.example.ehu.weekckech.presenter.presenter

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.ehu.weekckech.data.sql.TaskDataModel
import com.example.ehu.weekckech.data.sql.room.AppDatabase
import com.example.ehu.weekckech.presenter.contract.PagerDayConstract
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread


class PagerDayPresenter(val pagerDayView: PagerDayConstract.View, val mContext: Context) : PagerDayConstract.Presenter {
    lateinit var taskDataModel: TaskDataModel
    /**
     * 全てのタスクを表示する
     */
    override fun loadDaysTasks() {
        val list = ArrayList<TaskDataModel>()
        runBlocking {
            thread {
                // TODO ここで保存したリストを取得し、引数として渡す。
                val db = Room.databaseBuilder(mContext, AppDatabase::class.java, "database-name").build()
                val result = db.roomTaskDao().getAll()

                result.forEach { list.add(TaskDataModel(it.isChecked, it.detail, it.limitTime, it.notificationTime, it.weekGroup)) }
            }
        }
        list.forEach { Log.d("DBInfo", "load:$it") }
        pagerDayView.showDaysTasks(list)
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
    }

}