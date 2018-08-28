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
        list.add(DayListItemModel(false, "タイトル1月", "詳細1","月"))
        list.add(DayListItemModel(false, "タイトル2月", "詳細2","月"))
        list.add(DayListItemModel(false, "タイトル3月", "詳細3","月"))
        list.add(DayListItemModel(false, "タイトル4火", "詳細4","火"))
        list.add(DayListItemModel(false, "タイトル5火", "詳細5","火"))
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