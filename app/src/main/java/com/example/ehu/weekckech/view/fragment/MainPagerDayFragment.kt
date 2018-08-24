package com.example.ehu.weekckech.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.presenter.contract.PagerDayConstract


class MainPagerDayFragment : Fragment(), PagerDayConstract.View {
    override lateinit var presenter: PagerDayConstract.Presenter

    override fun showDaysTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAddEditTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDayTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pager_day, container, false)
    }
}
