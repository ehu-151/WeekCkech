package com.example.ehu.weekckech.view.fragment


import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.DayListItemModel
import com.example.ehu.weekckech.presenter.adapter.TasksAdapter
import com.example.ehu.weekckech.presenter.contract.PagerDayConstract
import java.util.*
import kotlin.collections.ArrayList


class MainPagerDayFragment : Fragment(), PagerDayConstract.View {
    override lateinit var presenter: PagerDayConstract.Presenter
    private lateinit var mContext: Context
    override fun showDaysTasks() {
        DayListItemModel(true,"1","1")
    }

    override fun showAddEditTask() {
        var temp=""
    }

    override fun showDayTasks() {
        var temp=""
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pager_day, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var listView :ListView=view.findViewById(R.id.listView)

        mContext=view.context

        var listItem=ArrayList<DayListItemModel>()
        listItem.add(DayListItemModel(false,"タイトル1","詳細1"))
        listItem.add(DayListItemModel(true,"タイトル2","詳細2"))
        listItem.add(DayListItemModel(false,"タイトル3","詳細3"))

        var tasksAdapter=TasksAdapter(mContext, listItem)
        listView.adapter=tasksAdapter



        // ListItemのセット
        showDayTasks()


    }
}
