package com.example.ehu.weekckech.presenter.contract

import com.example.ehu.weekckech.BasePresenter
import com.example.ehu.weekckech.BaseView

interface AddEditTaskContract {
    interface View: BaseView<AddEditTaskContract.Presenter> {
        fun showTaskConfigEditRow()
        fun showTasksMain()
    }
    interface Presenter: BasePresenter {
        fun saveTask()
    }
}