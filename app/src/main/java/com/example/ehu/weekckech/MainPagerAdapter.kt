package com.example.ehu.weekckech

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val tabTitles = arrayOf<CharSequence>("タブ1", "タブ2")

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> MainPagerDayFragment()
            1 -> MainPagerWeekFragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return tabTitles.size
    }
}