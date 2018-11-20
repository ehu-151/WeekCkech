package com.example.ehu.weekckech.presenter.contract

import com.example.ehu.weekckech.BasePresenter
import com.example.ehu.weekckech.BaseView
import com.example.ehu.weekckech.data.sql.TaskDataModel

interface PagerDayConstract {
    interface View : BaseView<Presenter> {
        fun showDaysTasks(taskDataModel: ArrayList<TaskDataModel>)
        fun showAddEditTask()
        fun showDayTasks()
    }

    interface Presenter : BasePresenter {
        fun loadDaysTasks()
        fun completeDaysTask()
        fun activateDaysTask()
        fun addNewDayTask()
        fun clearCompleteTasks()
    }
}