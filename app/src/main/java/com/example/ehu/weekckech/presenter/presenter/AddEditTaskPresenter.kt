package com.example.ehu.weekckech.presenter.presenter

import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.AddEditTaskItemModel
import com.example.ehu.weekckech.presenter.contract.AddEditTaskContract
import java.util.*

class AddEditTaskPresenter(val addEditTaskView: AddEditTaskContract.View) : AddEditTaskContract.Presenter {
    override fun loadTaskConfigEditRow() {
        var list = ArrayList<AddEditTaskItemModel>()
        val model = AddEditTaskItemModel
        list.add(AddEditTaskItemModel(R.drawable.ic_title_white_24dp, model.TEXTVIEW, "タイトル"))
        list.add(AddEditTaskItemModel(R.drawable.ic_notes_white_24dp, model.TEXTVIEW, "詳細"))
        list.add(AddEditTaskItemModel(R.drawable.ic_access_time_white_24dp, model.TEXTVIEW, "期限"))
        list.add(AddEditTaskItemModel(R.drawable.ic_notifications_white_24dp, model.SPINNER, "通知時間", spinnerItem = arrayListOf("1H前", "2H前", "12時")))
        addEditTaskView.showTaskConfigEditRow(list)
    }

    override fun saveTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        loadTaskConfigEditRow()
    }

}