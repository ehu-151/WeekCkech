package com.example.ehu.weekckech.presenter.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.ehu.weekckech.view.fragment.MainPagerDayFragment
import com.example.ehu.weekckech.view.fragment.MainPagerWeekFragment

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> MainPagerDayFragment()
            1 -> MainPagerWeekFragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return 2
    }
}