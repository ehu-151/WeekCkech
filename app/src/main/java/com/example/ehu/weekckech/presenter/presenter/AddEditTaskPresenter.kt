package com.example.ehu.weekckech.presenter.presenter

import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.AddEditTaskItemModel
import com.example.ehu.weekckech.data.sql.TaskDataModel
import com.example.ehu.weekckech.presenter.contract.AddEditTaskContract

class AddEditTaskPresenter(val addEditTaskView: AddEditTaskContract.View) : AddEditTaskContract.Presenter {
    override fun getEditTaskData() {
        var lists= listOf<Int>(R.id.edit_include_title,R.id.edit_include_detail,R.id.edit_include_limittime,
            R.id.edit_include_notificationtime,R.id.edit_include_weekgroup)
        for (list in lists){
            when (list){
            }
        }
    }

    override fun loadTaskConfigEditRow() {
        var lists = java.util.ArrayList<AddEditTaskItemModel>()
        val model = AddEditTaskItemModel
        lists.add(AddEditTaskItemModel(R.id.edit_include_title, R.drawable.ic_title_white_24dp, model.EDITTEXT, hintText = "タイトル"))
        lists.add(AddEditTaskItemModel(R.id.edit_include_detail, R.drawable.ic_notes_white_24dp, model.EDITTEXT, hintText = "詳細"))
        lists.add(AddEditTaskItemModel(R.id.edit_include_limittime, R.drawable.ic_access_time_white_24dp, model.TEXTVIEW,text = "12:00"))
        lists.add(AddEditTaskItemModel(R.id.edit_include_notificationtime, R.drawable.ic_notifications_white_24dp, model.SPINNER, spinnerItem = arrayListOf("1H前", "2H前", "12時")))
        lists.add(AddEditTaskItemModel(R.id.edit_include_weekgroup, R.drawable.ic_today_white_24dp, model.SPINNER, spinnerItem = arrayListOf("月", "火", "水", "木", "金", "土", "日")))
        addEditTaskView.showTaskConfigEditRow(lists)
    }

    override fun saveTask(taskDataModel: TaskDataModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        loadTaskConfigEditRow()
    }

}