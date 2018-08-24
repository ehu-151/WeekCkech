package com.example.ehu.weekckech.presenter.contract

import com.example.ehu.weekckech.BasePresenter
import com.example.ehu.weekckech.BaseView

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