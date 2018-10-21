package com.example.ehu.weekckech.presenter.contract

import com.example.ehu.weekckech.BasePresenter
import com.example.ehu.weekckech.BaseView
import com.example.ehu.weekckech.data.sql.AddEditTaskItemModel
import com.example.ehu.weekckech.data.sql.TaskDataModel

interface AddEditTaskContract {
    interface View: BaseView<AddEditTaskContract.Presenter> {
        fun showTaskConfigEditRow(listItemModel:ArrayList<AddEditTaskItemModel>)
        fun showTasksMain()
    }
    interface Presenter: BasePresenter {
        fun saveTask(taskDataModel: TaskDataModel)
        fun loadTaskConfigEditRow()
        fun getEditTaskData()
    }
}