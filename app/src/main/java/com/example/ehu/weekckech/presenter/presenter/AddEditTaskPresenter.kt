package com.example.ehu.weekckech.presenter.presenter

import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.AddEditTaskItemModel
import com.example.ehu.weekckech.presenter.contract.AddEditTaskContract
import java.util.*

class AddEditTaskPresenter(val addEditTaskView: AddEditTaskContract.View) : AddEditTaskContract.Presenter {
    override fun loadTaskConfigEditRow() {
        var lists = java.util.ArrayList<AddEditTaskItemModel>()
        val model = AddEditTaskItemModel
        lists.add(AddEditTaskItemModel(R.id.edit_include_title, R.drawable.ic_title_white_24dp, model.TEXTVIEW, "タイトル"))
        lists.add(AddEditTaskItemModel(R.id.edit_include_detail, R.drawable.ic_notes_white_24dp, model.TEXTVIEW, "詳細"))
        lists.add(AddEditTaskItemModel(R.id.edit_include_limittime, R.drawable.ic_access_time_white_24dp, model.TEXTVIEW, "期限"))
        lists.add(AddEditTaskItemModel(R.id.edit_include_notificationtime, R.drawable.ic_notifications_white_24dp, model.SPINNER, spinnerItem = arrayListOf("1H前", "2H前", "12時")))
        lists.add(AddEditTaskItemModel(R.id.edit_include_weekgroup, R.drawable.ic_today_white_24dp, model.SPINNER, spinnerItem = arrayListOf("月", "火", "水", "木", "金", "土", "日")))
        addEditTaskView.showTaskConfigEditRow(lists)
    }

    override fun saveTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        loadTaskConfigEditRow()
    }

}