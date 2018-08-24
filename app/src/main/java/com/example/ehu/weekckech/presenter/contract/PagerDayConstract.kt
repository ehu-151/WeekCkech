package com.example.ehu.weekckech.presenter.contract

import android.content.Context
import com.example.ehu.weekckech.BasePresenter
import com.example.ehu.weekckech.BaseView
import com.example.ehu.weekckech.presenter.adapter.TasksAdapter

interface PagerDayConstract {
    interface View:BaseView<Presenter>{
        fun showDaysTasks()
        fun showAddEditTask()
        fun showDayTasks()

    }
    interface Presenter:BasePresenter{
        fun showDaysTasks()
        fun completeDaysTask()
        fun activateDaysTask()
        fun addNewDayTask()
        fun clearCompleteTasks()
    }
}