package com.example.ehu.weekckech.presenter.contract

import androidx.lifecycle.MutableLiveData
import com.example.ehu.weekckech.BasePresenter
import com.example.ehu.weekckech.BaseView
import com.example.ehu.weekckech.data.sql.TaskDataModel

interface PagerDayConstract {
    interface View : BaseView<Presenter> {
        fun showDaysTasks(taskDataModel: MutableLiveData<ArrayList<TaskDataModel>>)
        fun showAddTask()
        fun showEditTask(model: TaskDataModel)
        fun showDayTasks()
        fun showSwitchNoTaskWarning(isWarning: Boolean)
    }

    interface Presenter : BasePresenter {
        fun loadDaysTasks()
        fun checkTask(taskId: Int)
        fun unCheckTask(taskId: Int)
        fun addNewDayTask()
        fun editDayTask(model: TaskDataModel)
        fun clearCompleteTasks()
    }
}