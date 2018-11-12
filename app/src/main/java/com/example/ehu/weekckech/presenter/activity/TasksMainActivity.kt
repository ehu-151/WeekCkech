package com.example.ehu.weekckech.presenter.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.presenter.adapter.MainPagerAdapter
import com.google.android.material.tabs.TabLayout

class TasksMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // PagerApdapterのセット
        val mainPagerAdapter = MainPagerAdapter(getSupportFragmentManager())
        // ViewPagerリソース
        val viewPager: ViewPager = findViewById(R.id.viewpager)
        // Tablayoutリソースのセット
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        // ViewPagerリソースにFragmentManager用のMainPagerAdapterをセット
        viewPager.adapter = mainPagerAdapter
        // TablayoutリソースのセットにViewPagerリソースをセット
        tabLayout.setupWithViewPager(viewPager)
        // Tablayoutリソースのタイトルをセット
        tabLayout.getTabAt(0)?.text = resources.getString(R.string.tab_day)
        tabLayout.getTabAt(1)?.text = resources.getString(R.string.tab_week)
    }
}
