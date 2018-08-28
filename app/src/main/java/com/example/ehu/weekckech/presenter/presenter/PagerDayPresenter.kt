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
        list.add(DayListItemModel(false, "タイトル1", "詳細1","月"))
        list.add(DayListItemModel(false, "タイトル2", "詳細2","月"))
        list.add(DayListItemModel(false, "タイトル3", "詳細3","月"))
        list.add(DayListItemModel(false, "タイトル4", "詳細3","火"))
        list.add(DayListItemModel(false, "タイトル5", "詳細3","火"))
        list.add(DayListItemModel(false, "タイトル6", "詳細3","火"))
        list.add(DayListItemModel(false, "タイトル7", "詳細3","火"))
        list.add(DayListItemModel(false, "タイトル8", "詳細3","水"))
        list.add(DayListItemModel(false, "タイトル9", "詳細3","水"))
        list.add(DayListItemModel(false, "タイトル10", "詳細3","木"))
        list.add(DayListItemModel(false, "タイトル11", "詳細3","木"))
        list.add(DayListItemModel(false, "タイトル12", "詳細3","木"))
        list.add(DayListItemModel(false, "タイトル13", "詳細3","金"))
        list.add(DayListItemModel(false, "タイトル14", "詳細3","土"))
        list.add(DayListItemModel(false, "タイトル15", "詳細3","日"))
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