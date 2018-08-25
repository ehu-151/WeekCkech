package com.example.ehu.weekckech.presenter.presenter

import com.example.ehu.weekckech.data.sql.DayListItemModel
import com.example.ehu.weekckech.presenter.contract.PagerDayConstract
import java.util.*


class PagerDayPresenter(val pagerDayView: PagerDayConstract.View) : PagerDayConstract.Presenter {

    /**
     * 全てのタスクを表示する
     */
    override fun loadDaysTasks() {
        var list = ArrayList<DayListItemModel>()
        list.add(DayListItemModel(false, "タイトル1", "詳細1"))
        list.add(DayListItemModel(false, "タイトル2", "詳細2"))
        list.add(DayListItemModel(false, "タイトル3", "詳細3"))
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

    /**
     * プレゼンターが初めにやること
     *
     * タスクのリロード
     */
    override fun start() {
        loadDaysTasks()
    }

}