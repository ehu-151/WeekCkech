package com.example.ehu.weekckech.presenter.presenter

import com.example.ehu.weekckech.data.sql.DayListItemModel
import com.example.ehu.weekckech.presenter.contract.PagerDayConstract
import java.util.*


class PagerDayPresenter(val pagerDayView: PagerDayConstract.View) : PagerDayConstract.Presenter {

    override fun showDaysTasks() {
        var list = ArrayList<DayListItemModel>()
        list.add(DayListItemModel(false, "タイトル1", "詳細1"))
        pagerDayView.showDaysTasks(list)
    }

    override fun completeDaysTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun activateDaysTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addNewDayTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearCompleteTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        showDaysTasks()
    }

}