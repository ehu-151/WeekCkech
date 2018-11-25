package com.example.ehu.weekckech.presenter.contract

import android.content.Context
import com.example.ehu.weekckech.BasePresenter
import com.example.ehu.weekckech.BaseView
import com.example.ehu.weekckech.data.sql.AddEditTaskItemModel
import com.example.ehu.weekckech.data.sql.TaskDataModel

interface AddEditTaskContract {
    interface View : BaseView<AddEditTaskContract.Presenter> {
        fun setTaskConfigEditRow(listItemModel: ArrayList<AddEditTaskItemModel>)
        fun showTasksMain()
        fun showKeybord()
        fun hideKeybord()
    }

    interface Presenter : BasePresenter {
        fun saveTask(taskDataModel: TaskDataModel, mContext: Context)
        fun loadTaskConfigEditRow()
        fun getEditTaskData()
    }
}