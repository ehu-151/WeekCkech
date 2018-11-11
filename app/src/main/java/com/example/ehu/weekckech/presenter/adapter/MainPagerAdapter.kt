package com.example.ehu.weekckech.presenter.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.ehu.weekckech.view.fragment.MainPagerDayFragment
import com.example.ehu.weekckech.view.fragment.MainPagerWeekFragment

/**
 * 遷移するFragmentを保持、管理、遷移するアダプタークラス
 */
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