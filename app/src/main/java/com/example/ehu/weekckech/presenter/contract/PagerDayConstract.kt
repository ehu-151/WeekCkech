package com.example.ehu.weekckech.presenter.contract

import androidx.lifecycle.MutableLiveData
import com.example.ehu.weekckech.BasePresenter
import com.example.ehu.weekckech.BaseView
import com.example.ehu.weekckech.data.sql.TaskDataModel

interface PagerDayConstract {
    interface View : BaseView<Presenter> {
        fun showDaysTasks(taskDataModel: MutableLiveData<ArrayList<TaskDataModel>>)
        fun showAddTask()
        fun showEditTask(taskId: Int)
        fun showDayTasks()
    }

    interface Presenter : BasePresenter {
        fun loadDaysTasks()
        fun completeDaysTask()
        fun activateDaysTask()
        fun addNewDayTask()
        fun editDayTask(model: TaskDataModel)
        fun clearCompleteTasks()
    }
}