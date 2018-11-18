package com.example.ehu.weekckech.presenter.presenter

import com.example.ehu.weekckech.data.sql.DayListItemModel
import com.example.ehu.weekckech.presenter.contract.PagerDayConstract


class PagerDayPresenter(val pagerDayView: PagerDayConstract.View) : PagerDayConstract.Presenter {

    /**
     * 全てのタスクを表示する
     */
    override fun loadDaysTasks() {
        val list = ArrayList<DayListItemModel>()
        list.add(DayListItemModel(false, "詳細1", "月"))
        list.add(DayListItemModel(false, "詳細2", "月"))
        list.add(DayListItemModel(false, "詳細3", "月"))
        list.add(DayListItemModel(false, "詳細4", "火"))
        list.add(DayListItemModel(true, "詳細5", "火"))
        list.add(DayListItemModel(false, "詳細6", "水"))
        list.add(DayListItemModel(false, "詳細7", "木"))
        list.add(DayListItemModel(false, "詳細8", "金"))
        list.add(DayListItemModel(false, "詳細9", "土"))
        list.add(DayListItemModel(false, "詳細10", "日"))
        list.add(DayListItemModel(false, "詳細11", "日"))
        pagerDayView.showDaysTasks(list)
    }

    override fun completeDaysTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun activateDaysTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addNewDayTask() {
        pagerDayView.showAddEditTask()
    }

    override fun clearCompleteTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * プレゼンターが初めにやること
     *
     * タスクのリロード
     */
    override fun start() {
        loadDaysTasks()
    }

}