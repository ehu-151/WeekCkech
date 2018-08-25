package com.example.ehu.weekckech.presenter.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.presenter.adapter.MainPagerAdapter
import kotlinx.android.synthetic.main.pager_day_listitem.view.*

class TasksMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // PagerApdapterのセット
        val mainPagerAdapter = MainPagerAdapter(getSupportFragmentManager())
        // ViewPagerリソース
        val viewPager: ViewPager=findViewById(R.id.viewpager)
        // Tablayoutリソースのセット
        val tabLayout:TabLayout=findViewById(R.id.tabLayout)
        // ViewPagerリソースにFragmentManager用のMainPagerAdapterをセット
        viewPager.adapter = mainPagerAdapter
        // TablayoutリソースのセットにViewPagerリソースをセット
        tabLayout.setupWithViewPager(viewPager)
        // Tablayoutリソースのタイトルをセット
        tabLayout.getTabAt(0)?.text=resources.getString(R.string.tab_day)
        tabLayout.getTabAt(1)?.text=resources.getString(R.string.tab_week)
    }
}
