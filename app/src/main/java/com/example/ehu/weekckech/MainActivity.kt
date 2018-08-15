package com.example.ehu.weekckech

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainPagerAdapter = MainPagerAdapter(getSupportFragmentManager())
        val viewPager: ViewPager=findViewById(R.id.viewpager)
        val tabLayout:TabLayout=findViewById(R.id.tabLayout)
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = mainPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
